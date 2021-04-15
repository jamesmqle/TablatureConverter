package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"harmonic","pulloff", "hammer", "string", "fret"})
public class Technical implements Serializable {

    String string;
    String fret;

    ArrayList<HammerOn> hammer;
    ArrayList<PullOff> pulloff;

    Harmonic harmonic;

    public Technical() {}

    public Technical(String string, String fret) {
        this.string = string;
        this.fret = fret;
    }

    @XmlElement(name = "string")
    public String getString() {
        return this.string;
    }
    public void setString(String string) {
        this.string = string;
    }

    @XmlElement(name = "fret")
    public String getFret() {
        return this.fret;
    }
    public void setFret(String fret) {
        this.fret = fret;
    }

    @XmlElement(name = "hammer-on")
    public ArrayList<HammerOn> getHammer() {
        return hammer;
    }
    public void setHammer(ArrayList<HammerOn> h){
        hammer = h;
    }

    @XmlElement(name = "pulloff")
    public void setPulloff(ArrayList<PullOff> p){
        pulloff = p;
    }
    public ArrayList<PullOff> getPulloff() {
        return pulloff;
    }
    public void setPulloff(ArrayList<PullOff> p){
        pulloff = p;
    }

    @XmlElement
    public Harmonic getHarmonic() {
        return this.harmonic;
    }
    public void setHarmonic(Harmonic harmonic) {
        this.harmonic = harmonic;
    }

}
