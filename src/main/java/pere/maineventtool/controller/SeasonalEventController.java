package pere.maineventtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pere.maineventtool.domain.seasonalevent.dto.SeasonalEventRequest;
import pere.maineventtool.domain.seasonalevent.mapper.SeasonalEventMapper;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.domain.seasonalevent.repository.SeasonalEventRepository;
import pere.maineventtool.shared.XmlImporter;
import pere.maineventtool.domain.shared.validation.ValidationService;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventXml;
import pere.maineventtool.domain.seasonalevent.xml.SeasonalEventsXml;
import pere.maineventtool.shared.DateTool;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seasonal_event")
public class SeasonalEventController {
    private final SeasonalEventRepository repository;
    private final SeasonalEventMapper mapper;

    @Autowired
    public SeasonalEventController(SeasonalEventRepository repository, SeasonalEventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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
        @Valid @RequestBody SeasonalEventRequest dto,
        BindingResult validationResult
    ) {
        if (validationResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationService.parseResult(validationResult).getErrors());
        }

        SeasonalEvent seasonalEvent = this.mapper.map(dto);

        repository.save(seasonalEvent);

        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/seasonal_events/{id}").build(seasonalEvent.getId().toString())
        ).build();
    }

    @PutMapping("/{seasonalEventId}")
    public ResponseEntity updateEvent(
        @PathVariable UUID seasonalEventId,
        @Valid @RequestBody SeasonalEventRequest dto
    ) {
        Optional<SeasonalEvent> seasonalEventData = repository.findById(seasonalEventId);

        if (!seasonalEventData.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        SeasonalEvent seasonalEvent = seasonalEventData.get();

        repository.save(this.mapper.map(dto, seasonalEvent));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{seasonalEventId}")
    public ResponseEntity deleteEvent(@PathVariable UUID seasonalEventId) {
        Optional<SeasonalEvent> seasonalEventData = repository.findById(seasonalEventId);

        if (!seasonalEventData.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(seasonalEventData.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/import")
    public ResponseEntity importEvents(@RequestParam("file") MultipartFile file) throws IOException, JAXBException {
        SeasonalEventsXml data = new SeasonalEventsXml();
        XmlImporter<SeasonalEventsXml> importer = new XmlImporter<>(data);
        SeasonalEventsXml xml = importer.importFromInputStream(file.getInputStream(), SeasonalEventsXml.class);

        ArrayList<SeasonalEvent> events = new ArrayList<>();
        for (SeasonalEventXml element : xml.events) {
            System.out.println(element.id);
            events.add(new SeasonalEvent(
                    UUID.randomUUID(),
                    Integer.parseInt(element.id),
                    element.name,
                    element.type,
                    element.subType,
                    DateTool.parseStringDate(element.start, "yyyy-MM-dd HH:mm:ss"),
                    DateTool.parseStringDate(element.end, "yyyy-MM-dd HH:mm:ss")
            ));
        }

        repository.saveAll(events);

        System.out.println(String.format("Imported %d events.", xml.events.size()));

        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/seasonal_events").build().toUri()
        ).build();
    }

    @GetMapping("/export")
    public ResponseEntity exportEvents() throws JAXBException {
        List<SeasonalEventXml> eventsData = repository.findAll().stream()
                .map(event -> {
                    SeasonalEventXml element = new SeasonalEventXml();
                    element.id = event.getEventId().toString();
                    element.type = event.getType();
                    element.subType = event.getSubType();
                    element.name = event.getName();
                    element.start = DateTool.parseDate(event.getStartingTime(), "yyyy-MM-dd'T'HH:mm:ss'+00:00'");
                    element.end = DateTool.parseDate(event.getEndingTime(), "yyyy-MM-dd'T'HH:mm:ss'+00:00'");

                    return element;
                })
                .collect(Collectors.toList());

        SeasonalEventsXml xml = new SeasonalEventsXml();
        xml.events = eventsData;

        XmlImporter<SeasonalEventsXml> importer = new XmlImporter<>(xml);
        ByteArrayOutputStream output = importer.exportToFile();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"text.xml\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(output.toByteArray());
    }
}
