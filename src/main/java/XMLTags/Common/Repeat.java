package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Repeat implements Serializable {
    String direction;
    String winged;

    public Repeat(){ }

    public Repeat(String direction, String winged){
        this.direction = direction; // "forward"
        this.winged = winged;
    }

    @XmlAttribute(name = "direction")
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @XmlAttribute(name = "winged")
    public String getWinged() {
        return winged;
    }

    public void setWinged(String winged) {
        this.winged = winged;
    }

}
