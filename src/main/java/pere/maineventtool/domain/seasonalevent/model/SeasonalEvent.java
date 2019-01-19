package pere.maineventtool.domain.seasonalevent.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pere.maineventtool.shared.DateTool;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "seasonal_events")
@NoArgsConstructor
@Getter
@Setter
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

    @JsonGetter("startingTime")
    public String getParsedStartingTime() {
        return DateTool.parseDate(this.startingTime, "yyyy-MM-dd HH:mm:ss");
    }

    @JsonGetter("endingTime")
    public String getParsedEndingTime() {
        return DateTool.parseDate(this.endingTime, "yyyy-MM-dd HH:mm:ss");
    }
}
