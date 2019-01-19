package pere.maineventtool.domain.seasonalevent.mapper;

import org.springframework.stereotype.Component;
import pere.maineventtool.domain.seasonalevent.dto.SeasonalEventRequest;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.shared.DateTool;

import java.util.UUID;

@Component
public class SeasonalEventMapper {
    public SeasonalEvent map(SeasonalEventRequest dto) {
        return new SeasonalEvent(
            UUID.randomUUID(),
            Integer.decode(dto.getEventId()),
            dto.getName(),
            dto.getType(),
            dto.getSubType(),
            DateTool.parseStringDate(dto.getStartingTime(), "yyyy-MM-dd HH:mm"),
            DateTool.parseStringDate(dto.getEndingTime(), "yyyy-MM-dd HH:mm")
        );
    }

    public SeasonalEvent map(SeasonalEventRequest dto, SeasonalEvent seasonalEvent) {
        seasonalEvent.setEventId(Integer.parseInt(dto.getEventId()));
        seasonalEvent.setName(dto.getName());
        seasonalEvent.setType(dto.getType());
        seasonalEvent.setSubType(dto.getSubType());
        seasonalEvent.setStartingTime(DateTool.parseStringDate(dto.getStartingTime(), "yyyy-MM-dd HH:mm"));
        seasonalEvent.setEndingTime(DateTool.parseStringDate(dto.getEndingTime(), "yyyy-MM-dd HH:mm"));

        return seasonalEvent;
    }
}
