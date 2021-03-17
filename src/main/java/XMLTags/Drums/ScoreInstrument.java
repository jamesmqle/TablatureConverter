package XMLTags.Drums;

import javax.xml.bind.annotation.XmlElement;

public class ScoreInstrument {

    String id;
    String instrumentName;

    @XmlElement(name = "id")
    public void setID(String id) { this.id = id; }
    public String getID() { return this.id; }

    @XmlElement(name = "instrument-name")
    public void setInstrumentName(String name) { instrumentName = name; }
    public String getInstrumentName() { return instrumentName; }
}
