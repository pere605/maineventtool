package pere.maineventtool.domain.seasonalevent.model;

import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class EpisodeIdentity implements Serializable {
    public EpisodeIdentity() {}

    public EpisodeIdentity(Integer number, UUID seasonalEventId) {
        this.number = number;
        this.seasonalEventId = seasonalEventId;
    }

    private Integer number;
    @Type(type = "pg-uuid")
    private UUID seasonalEventId;

    public Integer getNumber() {
        return number;
    }

    public UUID getSeasonalEventId() {
        return seasonalEventId;
    }
}
