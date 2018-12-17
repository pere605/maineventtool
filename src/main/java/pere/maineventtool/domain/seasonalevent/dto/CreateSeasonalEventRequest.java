package pere.maineventtool.domain.seasonalevent.dto;

import javax.validation.constraints.NotBlank;

public class CreateSeasonalEventRequest {
    @NotBlank
    public String eventId;
    @NotBlank
    public String name;
    @NotBlank
    public String type;
    @NotBlank
    public String subType;
    @NotBlank
    public String startingTime;
    @NotBlank
    public String endingTime;
}
