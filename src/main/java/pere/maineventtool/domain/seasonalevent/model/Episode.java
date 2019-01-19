package pere.maineventtool.domain.seasonalevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "episodes")
@NoArgsConstructor
@Getter
@Setter
public class Episode {
    @Id
    private UUID id;
    private Integer number;
    private UUID seasonalEventId;
    private String rewardType;
    private String rewardSubType;
    private Integer rewardAmount;
    private String chestId;

    public Episode(
            UUID id,
            Integer number,
            UUID seasonalEventId,
            String rewardType,
            String rewardSubType,
            Integer rewardAmount,
            String chestId
    ){
        this.id = id;
        this.number = number;
        this.seasonalEventId = seasonalEventId;
        this.rewardType = rewardType;
        this.rewardSubType = rewardSubType;
        this.rewardAmount = rewardAmount;
        this.chestId = chestId;
    }
}
