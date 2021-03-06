package XMLTags.Common;

/**
 * Tuan -
 * Song object class
 * All tags are their own objects, and are turned into tags in the ConvertedSongTest.java file through marshaling
 *
 * Jaxb is used to label each xml element and marshal each tag.
 *
 */

import java.io.Serializable;
import java.util.*;
import Parser.NoteConvert;
import XMLTags.Guitar.Notation;
import XMLTags.Guitar.Technical;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "score-partwise")
@XmlType(propOrder = {"work", "partList", "parts"})
public class ConvertedSong implements Serializable {
    @XmlAttribute
    private static final String VERSION = "3.1";

    //private Attributes attributes;
    private PartList partList;
    private List<Part> parts;
    private Work work;
    // private List<Measure> measures;
    // private List<Note> notes;

    public ConvertedSong() {
        this.parts = new ArrayList<Part>();
        this.partList = new PartList();
        //  this.measures = new ArrayList<Measure>();
        //  this.notes = new ArrayList<Note>();
    }

    public void addPart(Part part){
        this.parts.add(part);
        this.partList.getScorePart().setId("G" + this.parts.size());
        this.partList.getScorePart().setPartName("Guitar " + this.parts.size());
    }

    @XmlElement(name = "part-list")
    public PartList getPartList() {
        return partList;
    }

    public void setPartList(PartList partList) {
        this.partList = partList;
    }

    @XmlElement(name = "part")
    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @XmlElement(name = "work")
    public Work getWork(){
        return this.work;
    }

    public void setWork(Work work){
        this.work = work;
    }

    public Part getLastPart() {
        return this.parts.get(this.parts.size()-1);
    }

    public void addNoteToMeasure(String tuning, int fret){
        int stringNum = NoteConvert.convertToStringInt(tuning);
        this.getParts().get(this.getParts().size()-1).getMeasures().get(this.getParts().get(this.getParts().size()-1).getMeasures().size()-1).addNoteFromTab(tuning, fret);
        this.getParts().get(this.getParts().size()-1).getMeasures().get(this.getParts().get(this.getParts().size()-1).getMeasures().size()-1).getNotes().get(this.getParts().get(this.getParts().size()-1).getMeasures().get(this.getParts().get(this.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1).setNotations(new Notation(new Technical(String.valueOf(stringNum), String.valueOf(fret))));
    }

    public void addDrumNoteToMeasure(String tuning, String openClosed){
        Note noteToAdd = NoteConvert.convertToDrumNote(tuning, openClosed);
        this.getParts().get(this.getParts().size()-1).getMeasures().get(this.getParts().get(this.getParts().size()-1).getMeasures().size()-1).addNote(noteToAdd);
    }

}