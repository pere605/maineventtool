package pere.maineventtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import pere.maineventtool.domain.seasonalevent.dto.AddEpisodesRequest;
import pere.maineventtool.domain.seasonalevent.mapper.EpisodeMapper;
import pere.maineventtool.domain.seasonalevent.model.Episode;
import pere.maineventtool.domain.seasonalevent.repository.EpisodeRepository;
import pere.maineventtool.domain.seasonalevent.xml.EpisodeXml;
import pere.maineventtool.domain.seasonalevent.xml.EpisodesXml;
import pere.maineventtool.shared.XmlImporter;
import pere.maineventtool.domain.shared.validation.ValidationService;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/episode")
public class EpisodeController {
    private final EpisodeRepository repository;
    private final EpisodeMapper mapper;

    @Autowired
    public EpisodeController(EpisodeRepository repository, EpisodeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("/{seasonalEventId}")
    public ResponseEntity getEpisodes(@PathVariable UUID seasonalEventId) {
        return ResponseEntity.ok(repository.findAllBySeasonalEventId(seasonalEventId));
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

        repository.saveAll(this.mapper.map(dto, seasonalEventId));

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/episode/{seasonalEventId}").build(seasonalEventId.toString())
        ).build();
    }

    @DeleteMapping("/{episodeId}")
    public ResponseEntity removeEpisode(@PathVariable UUID episodeId) {
        try {
            repository.deleteById(episodeId);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{episodeId}")
    public ResponseEntity updateEpisode(
        @PathVariable UUID episodeId,
        @Valid @RequestBody pere.maineventtool.domain.seasonalevent.dto.Episode dto
    ) {
        Optional<Episode> episodeData = repository.findById(episodeId);

        if (!episodeData.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Episode episode = episodeData.get();

        episode.setNumber(Integer.parseInt(dto.getNumber()));
        episode.setRewardType(dto.getRewardType());
        episode.setRewardSubType(dto.getRewardSubType());
        episode.setRewardAmount(Integer.parseInt(dto.getRewardAmount()));
        episode.setChestId(dto.getChestId());

        repository.save(episode);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/import/{seasonalEventId}")
    public ResponseEntity importEpisodes(
        @PathVariable UUID seasonalEventId,
        @RequestParam("file") MultipartFile file
    ) throws IOException, JAXBException {
        XmlImporter<EpisodesXml> importer = new XmlImporter<>();
        EpisodesXml xml = importer.importFromInputStream(file.getInputStream(), EpisodesXml.class);

        ArrayList<Episode> episodes = new ArrayList<>();
        for (EpisodeXml element : xml.rewards) {
            UUID id = UUID.randomUUID();
            episodes.add(
                new Episode(
                    id,
                    Integer.parseInt(element.number),
                    seasonalEventId,
                    element.rewardType,
                    element.rewardSubtype,
                    Integer.parseInt(element.rewardAmount),
                    element.chestId
                )
            );
        }

        repository.saveAll(episodes);

        System.out.println(String.format("Imported %d episodes.", xml.rewards.size()));

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/episodes").build().toUri()
        ).build();
    }
}
