package pere.maineventtool.domain.seasonalevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeasonalEventRepository extends JpaRepository<SeasonalEvent, UUID> {
    Optional<SeasonalEvent> findById(UUID id);
}
