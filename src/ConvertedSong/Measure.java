package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 *  The Measure program implements objects attributes and note
 */
public class Measure implements Serializable {

    private Attributes attributes;
   // private Note note;
    private List<Note> notes;

    public Measure(){}

    public Measure(Attributes a, Note n){
        this.attributes = a;
        this.notes = new ArrayList<Note>();
    }

    public Measure(Attributes a){
        this.attributes = a;
    }

    public Measure(Note n){
        this.notes = new ArrayList<Note>();
    }

    @XmlElement(name = "attributes")
    public Attributes getAttributes(){
        return this.attributes;
    }

    public void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }

    public void addNote(Note note){
        this.notes.add(note);
    }


//    @XmlElement(name = "note")
//    public Note getNote(){
//        return this.note;
//    }
//
//    public void setNote(Note note){
//        this.note = note;
//    }

}
