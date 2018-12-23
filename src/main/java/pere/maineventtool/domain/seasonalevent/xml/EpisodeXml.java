package pere.maineventtool.domain.seasonalevent.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "episodic_reward")
@XmlAccessorType(XmlAccessType.FIELD)
public class EpisodeXml {
    @XmlElement(name = "id")
    public String chestId;
    @XmlElement(name = "episode")
    public String number;
    @XmlElement(name = "type")
    public String rewardType;
    @XmlElement(name = "subtype")
    public String rewardSubtype;
    @XmlElement(name = "amount")
    public String rewardAmount;
}
