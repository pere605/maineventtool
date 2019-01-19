package pere.maineventtool.domain.seasonalevent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class SeasonalEventRequest {
    @NotBlank
    private String eventId;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String subType;
    @NotBlank
    private String startingTime;
    @NotBlank
    private String endingTime;
}
