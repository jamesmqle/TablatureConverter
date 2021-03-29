package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Tied {
    String type;

    public Tied() {}

    public Tied(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
