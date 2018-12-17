package pere.maineventtool.domain.seasonalevent.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {
    @EmbeddedId
    private EpisodeIdentity identity;
    private String rewardType;
    private String rewardSubType;
    private Integer rewardAmount;
    private String chestId;

    public Episode() {}

    public Episode(
        EpisodeIdentity identity,
        String rewardType,
        String rewardSubType,
        Integer rewardAmount,
        String chestId
    ) {
        this.identity = identity;
        this.rewardType = rewardType;
        this.rewardSubType = rewardSubType;
        this.rewardAmount = rewardAmount;
        this.chestId = chestId;
    }

    public EpisodeIdentity getIdentity() {
        return identity;
    }

    public String getRewardType() {
        return rewardType;
    }

    public String getRewardSubType() {
        return rewardSubType;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public String getChestId() {return chestId;}
}
