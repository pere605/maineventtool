package pere.maineventtool.domain.seasonalevent.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pere.maineventtool.domain.seasonalevent.model.Episode;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class EpisodeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EpisodeRepository repository;

    @Test
    public void testFindAllBySeasonalEventId() {
        // Given
        Episode episode = new Episode(
                UUID.randomUUID(),
                1,
                UUID.randomUUID(),
                "item",
                "INS_RS_1",
                1,
                "some_chest"
        );

        entityManager.persist(episode);
        entityManager.flush();

        // When
        List<Episode> foundEpisodes = repository.findAllBySeasonalEventId(episode.getSeasonalEventId());

        // Then
        Assertions.assertEquals(1, foundEpisodes.size());

        Episode foundEpisode = foundEpisodes.get(0);

        Assertions.assertEquals(episode.getId(), foundEpisode.getId());
        Assertions.assertEquals(episode.getNumber(), foundEpisode.getNumber());
        Assertions.assertEquals(episode.getSeasonalEventId(), foundEpisode.getSeasonalEventId());
        Assertions.assertEquals(episode.getRewardType(), foundEpisode.getRewardType());
        Assertions.assertEquals(episode.getRewardSubType(), foundEpisode.getRewardSubType());
        Assertions.assertEquals(episode.getRewardAmount(), foundEpisode.getRewardAmount());
        Assertions.assertEquals(episode.getChestId(), foundEpisode.getChestId());
    }
}
