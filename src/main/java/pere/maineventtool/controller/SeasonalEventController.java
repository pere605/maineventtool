package pere.maineventtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pere.maineventtool.domain.seasonalevent.dto.CreateSeasonalEventRequest;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.domain.seasonalevent.repository.SeasonalEventRepository;
import pere.maineventtool.domain.seasonalevent.xml.XmlImporter;
import pere.maineventtool.domain.shared.validation.ValidationService;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventXml;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventsXml;
import pere.maineventtool.util.DateTool;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/seasonal_event")
public class SeasonalEventController {
    @Autowired
    private SeasonalEventRepository repository;

    @GetMapping
    public ResponseEntity getEvents() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") UUID id) {
        Optional<SeasonalEvent> seasonalEvent = repository.findById(id);

        if (seasonalEvent.isPresent()) {
            return ResponseEntity.ok(seasonalEvent);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createEvent(
        @Valid @RequestBody CreateSeasonalEventRequest dto,
        BindingResult validationResult
    ) {
        if (validationResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationService.parseResult(validationResult).getErrors());
        }

        UUID id = UUID.randomUUID();
        try {
            SeasonalEvent seasonalEvent = new SeasonalEvent(
                    id,
                    Integer.parseInt(dto.eventId),
                    dto.name,
                    dto.type,
                    dto.subType,
                    new SimpleDateFormat().parse(dto.startingTime),
                    new SimpleDateFormat().parse(dto.endingTime)
            );

            repository.save(seasonalEvent);
        } catch (ParseException e) {
            System.out.println("Problem parsing dates");
        }

        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/seasonal_events/{id}").build(id.toString())
        ).build();
    }

    @PostMapping("/import")
    public ResponseEntity importEvents(@RequestParam("file") MultipartFile file) throws IOException, JAXBException {
        SeasonalEventsXml data = (SeasonalEventsXml) XmlImporter.importFromInputStream(file.getInputStream(), SeasonalEventsXml.class);
        ArrayList<SeasonalEvent> events = new ArrayList<>();
        for (SeasonalEventXml element : data.events) {
            events.add(new SeasonalEvent(
                    UUID.randomUUID(),
                    Integer.parseInt(element.id),
                    element.name,
                    element.type,
                    element.subType,
                    DateTool.parseStringDate(element.start),
                    DateTool.parseStringDate(element.end)
            ));
        }

        repository.saveAll(events);

        System.out.println(String.format("Imported %d events.", data.events.size()));

        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/seasonal_events").build().toUri()
        ).build();
    }
}
