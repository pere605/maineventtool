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
import pere.maineventtool.domain.seasonalevent.dto.AddEpisodesRequest;
import pere.maineventtool.domain.seasonalevent.model.Episode;
import pere.maineventtool.domain.seasonalevent.model.EpisodeIdentity;
import pere.maineventtool.domain.seasonalevent.repository.EpisodeRepository;
import pere.maineventtool.domain.shared.validation.ValidationService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/episode")
public class EpisodeController {
    @Autowired
    private EpisodeRepository repository;

    @GetMapping("/{seasonalEventId}")
    public ResponseEntity getEpisodes(@PathVariable UUID seasonalEventId) {
        return ResponseEntity.ok(repository.findAllByIdentitySeasonalEventId(seasonalEventId));
    }

    @PostMapping("/{seasonalEventId}")
    public ResponseEntity addEpisodes(
        @PathVariable UUID seasonalEventId,
        @Valid @RequestBody AddEpisodesRequest dto,
        BindingResult validationResult
    ) {
        if (validationResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationService.parseResult(validationResult).getErrors());
        }

        for (pere.maineventtool.domain.seasonalevent.dto.Episode episodeDTO : dto.getEpisodes()) {
            repository.save(
                new Episode(
                    new EpisodeIdentity(Integer.parseInt(episodeDTO.getNumber()), seasonalEventId),
                    episodeDTO.getRewardType(),
                    episodeDTO.getRewardSubType(),
                    Integer.parseInt(episodeDTO.getRewardAmount()),
                    episodeDTO.getChestId()
                )
            );
        }

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/seasonal_events/{id}").build(seasonalEventId.toString())
        ).build();
    }
}
