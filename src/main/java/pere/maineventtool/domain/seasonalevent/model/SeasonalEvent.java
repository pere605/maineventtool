package pere.maineventtool.domain.seasonalevent.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Date startingTime;
    private Date endingTime;

    public SeasonalEvent() {}

    public SeasonalEvent(
        UUID id,
        Integer eventId,
        String name,
        String type,
        String subType,
        Date startingTime,
        Date endingTime
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

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getStartingTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startingTime);
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(endingTime);
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }
}
