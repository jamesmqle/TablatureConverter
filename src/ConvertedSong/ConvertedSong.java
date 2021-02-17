package ConvertedSong;

/**
 * <p>
 * This file is the object file that declares all sections of the MusicXML file format.
 * <p>
 * REFERENCE: MusicXML HelloWorld Element Structure
 * <p>
 * 1. score-partwise
 * a. part-list
 * i. score-part id = "P1" ..... /score-part
 * b. part id="P1"
 * i. measure number = "1"
 * 1. attributes
 * a. divisions ..... /divisions
 * b. key ..... /key
 * c. time ..... /time
 * d. clef
 * i. sign ..... /sign
 * ii. line ..... /line
 * 2. note ..... /note
 * a. pitch
 * i. step
 * ii. octave
 * b. duration ..... /duration
 * c. type ..... /type
 * /measure
 * /part
 * /score-partwise
 * <p>
 * Tag explanations:
 * This is not a comprehensive summary, and needs reference to the Hello world program to make full sense.
 * Hello world program is listed here: https://www.musicxml.com/tutorial/hello-world/
 * <p>
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>: Standard XML declaration for all XML documents
 * <p>
 * score-partwise: Root document type, consists of musical "parts".
 * part-list: ???
 * score-part: the listing of a musical part (the "declaration"), where the id refers to which measure the part refers to
 * part: the tag that contains all details of the measure
 * measure: indicates the start of a measure, as well as defines which measure it is with an ID (eg, p1)
 * attributes: the tag in which all the musical notes, stylings, key, time signature go
 * divisions: ???
 * key: indicates the key of a song. MusicXML uses several tags to define key, but in the
 * hello world program, the <fifths></fifths> tag is used. The fifths tag defines the
 * key based on how many flats it has.
 * time: sets up time signature
 * clef: indicates which clef is used, and which line to put it on for the final score.
 * note: define the start of a note
 * pitch: define the pitch of a note with the following tags:
 * step: define the letter-pitch of a note (Ab-G#)
 * octave: define the octave that the note is in
 * duration: defines length as 1 division (tag above) per quarter note
 * type: states what note is being played. Is to some extent redundant, but is helpful for
 */

import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

//@XmlRootElement

@XmlRootElement(name = "score-partwise")
public class ConvertedSong implements Serializable {
    @XmlAttribute
    public static final String VERSION = "3.1";

    //private Attributes attributes;
    private PartList partList;
    private List<Part> parts;
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
    }

//    public void addMeasure(Measure measure){
//        this.measures.add(measure);
//    }

  //  public void addNote(Note note){
  //      this.notes.add(note);
 //   }

    /*
    @XmlElement(name = "attributes")
    public Attributes getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
*/

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

    public void addNoteToMeasure(Measure measure){
        this.getParts().get(this.getParts().size()-1).getMeasures().get(this.getParts().size()-1).addNoteFromTab("e", 1);
    }

    /*
     * Attributes (attribute)
     * Fields:
     * - Key (key)
     * - TimeSignature (time)
     * - Clef (clef
     */

}