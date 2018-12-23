package pere.maineventtool.domain.seasonalevent.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "episodic_rewards")
@XmlAccessorType(XmlAccessType.FIELD)
public class EpisodesXml {
    @XmlElement(name = "episodic_reward")
    public ArrayList<EpisodeXml> rewards;
}
