package pere.maineventtool.domain.seasonalevent.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "seasonal_events")
public class SeasonalEvent {
    @Id
    @Type(type = "pg-uuid")
    private UUID id;
    private Integer eventId;
    private String name;
    private String type;
    private String subType;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;

    public SeasonalEvent() {}

    public SeasonalEvent(
        UUID id,
        Integer eventId,
        String name,
        String type,
        String subType,
        LocalDateTime startingTime,
        LocalDateTime endingTime
    ) {
        this.id = id;
        this.eventId = eventId;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public UUID getId() {
        return id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }
}
