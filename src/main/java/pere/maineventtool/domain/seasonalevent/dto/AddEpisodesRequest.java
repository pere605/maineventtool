package pere.maineventtool.domain.seasonalevent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddEpisodesRequest {
    @Valid @JsonProperty(value = "episodes") private List<Episode> episodes;
}
