package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Measure implements Serializable {

    private Attributes attributes;
    private Note note;

    public Measure(){}

    public Measure(Attributes a, Note n){
        this.attributes = a;
        this.note = n;
    }

    public Measure(Attributes a){
        this.attributes = a;
    }

    public Measure(Note n){
        this.note = n;
    }

    @XmlElement(name = "Attribute")
    public Attributes getAttributes(){
        return this.attributes;
    }

    public void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }

    @XmlElement(name = "Note")
    public Note getNote(){
        return this.note;
    }

    public void setNote(Note note){
        this.note = note;
    }

}
