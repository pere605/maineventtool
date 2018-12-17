package pere.maineventtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pere.maineventtool.domain.seasonalevent.dto.CreateSeasonalEventRequest;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.domain.seasonalevent.repository.SeasonalEventRepository;
import pere.maineventtool.domain.shared.validation.ValidationService;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
        SeasonalEvent seasonalEvent = new SeasonalEvent(
            id,
            Integer.parseInt(dto.eventId),
            dto.name,
            dto.type,
            dto.subType,
            LocalDateTime.parse(dto.startingTime),
            LocalDateTime.parse(dto.endingTime)
        );

        repository.save(seasonalEvent);

        return ResponseEntity.created(
            UriComponentsBuilder.fromPath("/seasonal_events/{id}").build(id.toString())
        ).build();
    }
}
