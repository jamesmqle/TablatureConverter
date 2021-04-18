package XMLTags.Common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"displayStep", "displayOctave"})
public class Unpitched {
    String displayStep;
    String displayOctave;

    public Unpitched(){
    }

    public Unpitched(String displayStep, String displayOctave){
        this.displayOctave = displayOctave;
        this.displayStep = displayStep;
    }

    @XmlElement(name = "display-octave")
    public String getDisplayOctave() {
        return displayOctave;
    }

    public void setDisplayOctave(String displayOctave){
        this.displayOctave = displayOctave;
    }

    @XmlElement(name = "display-step")
    public String getDisplayStep(){
        return this.displayStep;
    }

    public void setDisplayStep(String displayStep){
        this.displayStep = displayStep;
    }
}
