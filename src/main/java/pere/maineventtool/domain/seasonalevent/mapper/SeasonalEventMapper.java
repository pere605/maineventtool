package pere.maineventtool.domain.seasonalevent.mapper;

import org.springframework.stereotype.Component;
import pere.maineventtool.domain.seasonalevent.dto.SeasonalEventRequest;
import pere.maineventtool.domain.seasonalevent.model.SeasonalEvent;
import pere.maineventtool.shared.DateTool;

import java.util.UUID;

@Component
public class SeasonalEventMapper {
    public SeasonalEvent map(SeasonalEventRequest dto) {
        UUID id = UUID.randomUUID();

        return new SeasonalEvent(
                id,
                Integer.parseInt(dto.eventId),
                dto.name,
                dto.type,
                dto.subType,
                DateTool.parseStringDate(dto.startingTime, "yyyy-MM-dd HH:mm"),
                DateTool.parseStringDate(dto.endingTime, "yyyy-MM-dd HH:mm")
        );
    }

    public SeasonalEvent map(SeasonalEventRequest dto, SeasonalEvent seasonalEvent) {
        seasonalEvent.setEventId(Integer.parseInt(dto.eventId));
        seasonalEvent.setName(dto.name);
        seasonalEvent.setType(dto.type);
        seasonalEvent.setSubType(dto.subType);
        seasonalEvent.setStartingTime(DateTool.parseStringDate(dto.startingTime, "yyyy-MM-dd HH:mm"));
        seasonalEvent.setEndingTime(DateTool.parseStringDate(dto.endingTime, "yyyy-MM-dd HH:mm"));

        return seasonalEvent;
    }
}
