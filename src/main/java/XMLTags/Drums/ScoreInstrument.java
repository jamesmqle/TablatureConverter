package XMLTags.Drums;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ScoreInstrument {

    String id;
    String instrumentName;

    public ScoreInstrument(){
    }

    public ScoreInstrument(String id, String instrumentName){
        this.id = id;
        this.instrumentName = instrumentName;
    }

    @XmlAttribute(name = "id")
    public void setID(String id) { this.id = id; }
    public String getID() { return this.id; }

    @XmlElement(name = "instrument-name")
    public void setInstrumentName(String name) { instrumentName = name; }
    public String getInstrumentName() { return instrumentName; }
}
