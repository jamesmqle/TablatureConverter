package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Harmonic implements Serializable {

    String natural;

    public Harmonic(String natural) {
        this.natural = natural;
    }

    public Harmonic() {
    }

    @XmlAttribute(name = "natural")
    public String getNatural(){
        return this.natural;
    }
    public void setNatural(){
        this.natural = natural;
    }
}
