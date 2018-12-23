package pere.maineventtool.domain.seasonalevent.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "events")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeasonalEventsXml {
    @XmlElement(name = "event")
    public ArrayList<SeasonalEventXml> events;
}
