package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"number", "type", "placement"})
public class Slur {

    int number;
    String type;
    String placement;

    public Slur() {}

    public Slur(int number, String type) {
        this.number = number;
        this.type = type;
        this.placement = placement;
    }

    public Slur(int number, String type, String placement) {
        this.number = number;
        this.type = type;
        this.placement = placement;
    }

    @XmlAttribute
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @XmlAttribute
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public String getPlacement() {
        return this.placement;
    }
    public void setPlacement(String placement) {
        this.placement = placement;
    }
}