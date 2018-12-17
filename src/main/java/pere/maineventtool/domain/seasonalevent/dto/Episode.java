package pere.maineventtool.domain.seasonalevent.dto;

import javax.validation.constraints.NotBlank;

public class Episode {
    @NotBlank
    private String number;
    @NotBlank
    private String rewardType;
    @NotBlank
    private String rewardSubType;
    @NotBlank
    private String rewardAmount;
    @NotBlank
    private String chestId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(String rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getChestId() {
        return chestId;
    }

    public void setChestId(String chestId) {
        this.chestId = chestId;
    }
}
