package pere.maineventtool.domain.seasonalevent.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class SeasonalEventRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SeasonalEventRepository repository;

    @Test
    public void testFindById() {
        // Given
        SeasonalEvent event = new SeasonalEvent(
                UUID.randomUUID(),
                1,
                "test",
                "test",
                "test",
                Date.from(Instant.now()),
                Date.from(Instant.now())
        );

        entityManager.persist(event);
        entityManager.flush();

        // When
        Optional<SeasonalEvent> foundEvent = repository.findById(event.getId());

        if (!foundEvent.isPresent()) {
            Assert.fail("No event found");
        }

        // Then
        Assertions.assertEquals(foundEvent.get().getId(), event.getId());
        Assertions.assertEquals(foundEvent.get().getEventId(), event.getEventId());
        Assertions.assertEquals(foundEvent.get().getName(), event.getName());
        Assertions.assertEquals(foundEvent.get().getType(), event.getType());
        Assertions.assertEquals(foundEvent.get().getSubType(), event.getSubType());
        Assertions.assertEquals(foundEvent.get().getStartingTime(), event.getStartingTime());
        Assertions.assertEquals(foundEvent.get().getEndingTime(), event.getEndingTime());
    }
}
