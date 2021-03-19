package XMLTags.Drums;

import javax.xml.bind.annotation.XmlElement;

public class Instrument {

    String id;

    @XmlElement(name = "id")
    public void setID(String id) {
        this.id = id;
    }
    public String getId(){
        return id;
    }
}