package XMLTags.Drums;

import javax.xml.bind.annotation.XmlElement;

public class Unpitched {

    String displayStep;
    String displayOctave;

    @XmlElement(name = "display-step")
    public void setDisplayStep(String ds) { displayStep = ds; }
    public String getDisplayStep() { return displayStep; }

    @XmlElement(name = "display-octave")
    public void setDisplayOctave(String dO) { displayOctave = dO; }
    public String getDisplayOctave() { return displayOctave; }

}
