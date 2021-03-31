package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Tie implements Serializable {
    private String type;

    public Tie() {}

    public Tie(String type) {
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