package pere.maineventtool.domain.seasonalevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pere.maineventtool.domain.seasonalevent.model.Episode;
import pere.maineventtool.domain.seasonalevent.model.EpisodeIdentity;

import java.util.List;
import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, EpisodeIdentity> {
    List<Episode> findAllByIdentitySeasonalEventId(UUID seasonalEventId);
}
