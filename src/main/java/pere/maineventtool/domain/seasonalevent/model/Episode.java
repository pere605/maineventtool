package pere.maineventtool.domain.seasonalevent.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    private UUID id;
    private Integer number;
    private UUID seasonalEventId;
    private String rewardType;
    private String rewardSubType;
    private Integer rewardAmount;
    private String chestId;

    public Episode() {}

    public Episode(
        UUID id,
        Integer number,
        UUID seasonalEventId,
        String rewardType,
        String rewardSubType,
        Integer rewardAmount,
        String chestId
    ) {
        this.id = id;
        this.number = number;
        this.seasonalEventId = seasonalEventId;
        this.rewardType = rewardType;
        this.rewardSubType = rewardSubType;
        this.rewardAmount = rewardAmount;
        this.chestId = chestId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSeasonalEventId() {
        return seasonalEventId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardSubType() {
        return rewardSubType;
    }

    public void setRewardSubType(String rewardSubType) {
        this.rewardSubType = rewardSubType;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getChestId() {
        return chestId;
    }

    public void setChestId(String chestId) {
        this.chestId = chestId;
    }
}
