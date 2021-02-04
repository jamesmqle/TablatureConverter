package Parser;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * @author Tuan
 * 
 * This file is the object file that declares all sections of the MusicXML file format.
 * 
 * REFERENCE: MusicXML HelloWorld Element Structure
 * 
 * 1. score-partwise
 * 	a. part-list
 * 	 i. score-part id = "P1" ..... /score-part
 *  b. part id="P1"
 *   i. measure number = "1"
 *    1. attributes
 *     a. divisions ..... /divisions
 *     b. key ..... /key 
 *     c. time ..... /time
 *     d. clef
 *      i. sign ..... /sign
 *      ii. line ..... /line
 *    2. note ..... /note
 *     a. pitch
 *      i. step
 *      ii. octave
 *     b. duration ..... /duration
 *     c. type ..... /type
 *   /measure
 *  /part
 * /score-partwise
 * 
 * Tag explanations: 
 * This is not a comprehensive summary, and needs reference to the Hello world program to make full sense. 
 * Hello world program is listed here: https://www.musicxml.com/tutorial/hello-world/
 * 
 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>: Standard XML declaration for all XML documents
 * 
 * score-partwise: Root document type, consists of musical "parts".
 *  part-list: ???
 *   score-part: the listing of a musical part (the "declaration"), where the id refers to which measure the part refers to
 *  part: the tag that contains all details of the measure
 *   measure: indicates the start of a measure, as well as defines which measure it is with an ID (eg, p1)
 *    attributes: the tag in which all the musical notes, stylings, key, time signature go
 *     divisions: ???
 *     key: indicates the key of a song. MusicXML uses several tags to define key, but in the 
 * 			hello world program, the <fifths></fifths> tag is used. The fifths tag defines the
 * 			key based on how many flats it has.
 *     time: sets up time signature
 *     clef: indicates which clef is used, and which line to put it on for the final score.
 *    note: define the start of a note
 *     pitch: define the pitch of a note with the following tags:
 *      step: define the letter-pitch of a note (Ab-G#)
 *      octave: define the octave that the note is in
 *     duration: defines length as 1 division (tag above) per quarter note
 *     type: states what note is being played. Is to some extent redundant, but is helpful for 
 *     
 *      
 */

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "score-partwise")
public class ConvertedSong implements Serializable{
	@XmlAttribute
	public static final String VERSION = "3.1";
	
    private TimeSignature timeSignature;
    private PartList partList;
    
    public ConvertedSong() {}

    public ConvertedSong(TimeSignature timeSignature) {
        this.timeSignature = timeSignature;
    }
	
	@XmlElement (name = "time")
	public TimeSignature getTimeSignature() {
		return timeSignature;
	}
	
	public void setTimeSignature(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
	}

	public void setPartList(PartList partList) {
		this.partList = partList;
	}
	
	//@XmlElement (name = "part-list")
	public PartList getPartList() {
		return partList;
	}
	
	/*
	 * Time Signature (time)
	 * Fields: 
	 * - beats (beats)
	 * - beatType (beat-type)
	 */
	
	public static class TimeSignature implements Serializable {

		private int beats;
		private int beatType;
		
		public TimeSignature() {
		}
		
		//@XmlElement (name = "beats")
		public int getBeats() {
			return this.beats;
		}
		
		public void setBeats(int beats) {
			this.beats = beats;
		}
		
		//@XmlElement (name = "beat-type")
		public int getBeatType() {
			return this.beatType;
		}
		
		public void setBeatType(int beatType) {
			this.beatType = beatType;
		}
		
		public TimeSignature(int beats, int beatType) {
			this.beats = beats;
			this.beatType = beatType;
		}
	}
	
	/*
	 * Part List (part-list)
	 * Fields:
	 * - scorePart (score-part id="P1")
	 */
	
	public static class PartList implements Serializable {
		//I think we need to implement the ScorePart below as a list
		private ScorePart scorePart;
		
		//@XmlElement (name = "score-part")
		public ScorePart getScorePart() {
			return this.scorePart;
		}
		
		public void setScorePart(ScorePart scorePart) {
			this.scorePart = scorePart;
		}
		
		public PartList() {
		}
		
		public PartList(ScorePart scorePart) {
			this.scorePart = scorePart;
		}
		
		/*
		 * Score Part (score-part)
		 * Attributes: 
		 * - id
		 * Fields:
		 * - partName (part-name)
		 */
		public static class ScorePart implements Serializable {
			//@XmlAttribute
			public static String id;
			private String partName;
			
			public ScorePart() {
			}
			
			//@XmlElement
			public String getId() {
				return this.id;
			}
			
			public void setId(String id) {
				this.id = id;
			}
			
			//@XmlElement (name = "part-name")
			public String getPartName() {
				return this.partName;
			}
			
			public void setPartName(String partName) {
				this.partName = partName;
			}
			
			public ScorePart(String partName, String id) {
				this.partName = partName;
				this.id = id;
			}
		}
	}
}