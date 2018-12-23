package pere.maineventtool.domain.seasonalevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pere.maineventtool.domain.seasonalevent.model.Episode;

import java.util.List;
import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
    List<Episode> findAllBySeasonalEventId(UUID seasonalEventId);
}
