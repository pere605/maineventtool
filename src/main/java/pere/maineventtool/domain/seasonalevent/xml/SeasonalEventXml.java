package pere.maineventtool.domain.seasonalevent.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeasonalEventXml {
    public String id;
    public String name;
    public String type;
    @XmlElement(name = "sub_type")
    public String subType;
    public String start;
    public String end;
}
