package XMLTags.Common;

import Parser.NoteConvert;
import XMLTags.Drums.Backup;

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
    private Barline barline; // for drums
    private Backup backup; // for drums

    public Measure(){
        this.attributes = new Attributes(new Clef(), new Key(0, "major"), new TimeSignature(4,4), "1", new StaffDetails());
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

    public Measure(Attributes a, List<Note> n, String number){
        this.attributes = a;
        this.notes = n;
        this.number = number;
    }

    /**
     * ============== FOR DRUMS ===============
     */
    public Measure(Attributes attributes, ArrayList<Note> note,String number, Barline barline, Backup backup){
        this.attributes = attributes;
        this.notes = note;
        this.number = number;
        this.barline = barline;
        this.backup = backup;
    }

    public void setMeasure(Measure measure){
        this.attributes = measure.attributes;
        this.notes = measure.notes;
        this.number = measure.number;
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

    public Note getLastNote(){
        return this.notes.get(this.notes.size()-1);
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
    }

    public void addNote(Note note){
        this.notes.add(note);
    }

    public void addNoteFromTab(String string, int fret){
        this.notes.add(new Note(new Pitch(NoteConvert.convertToNote(string, fret).getPitch().getStep(),
                NoteConvert.octaveFinder(string, fret),NoteConvert.convertToNote(string, fret).getPitch().getAlter()),
                1, "quarter"/*new Type("0", "1")*/));
    }

    @XmlAttribute
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * ============== FOR DRUMS ===============
     */

    @XmlElement(name = "barline")
    public Barline getBarline() {
        return barline;
    }
    public void setBarline(Barline barline) {
        this.barline = barline;
    }

    @XmlElement(name = "backup")
    public void setBackup(Backup backup) {
        this.backup = backup;
    }
    public Backup getBackup() {
        return backup;
    }


}
