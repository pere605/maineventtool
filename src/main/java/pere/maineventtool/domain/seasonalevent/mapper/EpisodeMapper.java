package pere.maineventtool.domain.seasonalevent.mapper;

import org.springframework.stereotype.Component;
import pere.maineventtool.domain.seasonalevent.dto.AddEpisodesRequest;
import pere.maineventtool.domain.seasonalevent.model.Episode;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class EpisodeMapper {
    public ArrayList<Episode> map(AddEpisodesRequest dto, UUID seasonalEventId) {
        ArrayList<Episode> episodes = new ArrayList<Episode>();

        for (pere.maineventtool.domain.seasonalevent.dto.Episode episodeDTO : dto.getEpisodes()) {
            UUID id = UUID.randomUUID();

            episodes.add(new Episode(
                id,
                Integer.parseInt(episodeDTO.getNumber()),
                seasonalEventId,
                episodeDTO.getRewardType(),
                episodeDTO.getRewardSubType(),
                Integer.parseInt(episodeDTO.getRewardAmount()),
                episodeDTO.getChestId()
            ));
        }

        return episodes;
    }
}
