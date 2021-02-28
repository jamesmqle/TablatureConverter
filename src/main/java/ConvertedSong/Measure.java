package ConvertedSong;

import Parser.NoteConvert;
import javax.management.Attribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 *  The Measure program implements objects attributes and note
 */
public class Measure implements Serializable {

    private Attributes attributes;
    private List<Note> notes;
    private String number;

    public Measure(){
        this.attributes = new Attributes();
        this.notes = new ArrayList<Note>();
        this.number = "1";
    }

    public Measure(Attributes a, List<Note> n){
        this.attributes = a;
        this.notes = n;
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

    @XmlElement(name = "note")
    public List<Note> getNotes(){
        return this.notes;
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
    }

    public void addNote(Note note){
        this.notes.add(note);
    }

    public void addNoteFromTab(String string, int fret){
   //     this.notes.add(new Note(new Pitch(NoteConvert.convertToNote(string, fret).getPitch().getStep(),NoteConvert.octaveFinder(string, fret),NoteConvert.convertToNote(string, fret).getPitch().getAlter()), 1, "quarter"/*new Type("0", "1")*/));
    }

    @XmlAttribute
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
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
