package pere.maineventtool.domain.seasonalevent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
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
}
