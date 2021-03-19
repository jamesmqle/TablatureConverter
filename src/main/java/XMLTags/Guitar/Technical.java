package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"string", "fret"})
public class Technical {

    int string;
    int fret;

    public Technical() {}

    public Technical(int string, int fret) {
        this.string = string;
        this.fret = fret;
    }

    @XmlElement(name = "string")
    public int getString() {
        return this.string;
    }
    public void setString(int string) {
        this.string = string;
    }

    @XmlElement(name = "fret")
    public int getFret() {
        return this.fret;
    }
    public void setFret(int fret) {
        this.fret = fret;
    }
}
