package XMLTags.Drums;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Instrument {

    String id;

    public Instrument(){
    }

    public Instrument (String id){
        this.id = id;
    }

    @XmlAttribute(name = "id")
    public String getId(){
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
}