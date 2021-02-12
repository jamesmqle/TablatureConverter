package Parser;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.Duration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Tuan
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

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "score-partwise")
public class ConvertedSong implements Serializable {
    @XmlAttribute
    public static final String VERSION = "3.1";

    private Attributes attributes;
    private Note note;
    private PartList partList;

    public ConvertedSong() {
    }

    @XmlElement(name = "attributes")
    public Attributes getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @XmlElement(name = "note")
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @XmlElement(name = "part-list")
    public PartList getPartList() {
        return partList;
    }

    public void setPartList(PartList partList) {
        this.partList = partList;
    }

    /*
     * Attributes (attribute)
     * Fields:
     * - Key (key)
     * - TimeSignature (time)
     * - Clef (clef
     */

    public static class Attributes implements Serializable {
		private int divisions;
        private Key key;
        private TimeSignature timeSignature;
        private Clef clef;

        public Attributes() {
        }

        public Attributes(Key key, TimeSignature timeSignature, Clef clef) {
            this.key = key;
            this.timeSignature = timeSignature;
            this.clef = clef;
        }

        public Attributes(Key key) {
            this.key = key;
        }

        public Attributes(Clef clef) {
            this.clef = clef;
        }

        public Attributes(TimeSignature timeSignature) {
            this.timeSignature = timeSignature;
        }

        @XmlElement(name = "key")
        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        @XmlElement(name = "time")
        public TimeSignature getTimeSignature() {
            return timeSignature;
        }

        public void setTimeSignature(TimeSignature timeSignature) {
            this.timeSignature = timeSignature;
        }

        @XmlElement(name = "clef")
        public Clef getClef() {
            return clef;
        }

        public void setClef(Clef clef) {
            this.clef = clef;
        }

        @XmlElement(name = "divisions")
		public int getDivisions() {
			return this.divisions;
		}

		public void setDivisions(int divisions) {
			this.divisions = divisions;
		}

        /*
         * Key (key)
         * Fields:
         * - fifths (fifths)
         */

        public static class Key implements Serializable {
            private int fifths;

            public Key() {
            }

            @XmlElement(name = "fifths")
            public int getFifths() {
                return this.fifths;
            }

            public void setFifths(int fifths) {
                this.fifths = fifths;
            }

            public Key(int fifths) {
                this.fifths = fifths;
            }
        }

        /*
         * Clef (clef)
         * Fields:
         * - sign (sign)
         * - line (line)
         */
        public static class Clef implements Serializable {
            private String sign;
            private int line;

            public Clef() {
            }

            public Clef(Clef clef) {
            }

            public Clef(String sign, int line) {
                this.sign = sign;
                this.line = line;
            }

            @XmlElement(name = "sign")
            public String getSign() {
                return this.sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            @XmlElement(name = "line")
            public int getLine() {
                return this.line;
            }

            public void setLine(int line) {
                this.line = line;
            }
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

            public TimeSignature(int beats, int beatType) {
                this.beats = beats;
                this.beatType = beatType;
            }

            @XmlElement(name = "beats")
            public int getBeats() {
                return this.beats;
            }

            public void setBeats(int beats) {
                this.beats = beats;
            }

            @XmlElement(name = "beat-type")
            public int getBeatType() {
                return this.beatType;
            }

            public void setBeatType(int beatType) {
                this.beatType = beatType;
            }
        }
    }

    /*
     * Note (note)
     * Fields:
     * - pitch (pitch)
     * - duration (duration)
     * - type (type)
     */
    public static class Note implements Serializable {
        private Pitch pitch;
        private int duration;
        private Type type;

        public Note() {
        }

        public Note(Pitch g) {
        	this.pitch = g;
        }

        public Note(int duration) {
        	this.duration = duration;
        }

        public Note(Type type) {
        	this.type = type;
        }

        public Note(Pitch pitch, int duration, Type type) {
            this.pitch = pitch;
            this.duration = duration;
            this.type = type;
        }


        @XmlElement(name = "pitch")
        public Pitch getPitch() {
            return this.pitch;
        }

        public void setPitch(Pitch pitch) {
            this.pitch = pitch;
        }

        @XmlElement(name = "duration")
        public int getDuration() {
            return this.duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        @XmlElement(name = "type")
        public Type getType() {
            return this.type;
        }

        public void setType(Type type) {
            this.type = type;
        }


        /*
         * Pitch (pitch)
         * Fields:
         * - step (step)
         * - octave (octave)
         */

        public static class Pitch implements Serializable {
            private String step;
            private int octave;

            public Pitch() {
            }

            //redundant?
            public Pitch(Pitch pitch) {
            	this.octave = pitch.octave;
            	this.step = pitch.step;
            }

            public Pitch(String step, int octave) {
                this.step = step;
                this.octave = octave;
            }

            @XmlElement(name = "step")
            public String getStep() {
                return this.step;
            }

            public void setsStep(String step) {
                this.step = step;
            }

            @XmlElement(name = "octave")
            public int getOctave() {
                return this.octave;
            }

            public void setOctave(int octave) {
                this.octave = octave;
            }
        }

        /*
         * Duration (duration)
         * Fields:
         * - division (represents a note's duration in terms of divisions per quarter note)
         */

        public static class Duration implements Serializable {
            private int divisions;

            public Duration() {
            }

            public Duration(int divisions) {
            	this.divisions = divisions;
            }

            public Duration(Duration duration) {
            	this.divisions = duration.divisions;
            }

            @XmlElement(name = "duration")
            public int getDivisions() {
                return this.divisions;
            }

            public void setDivisions(int divisions) {
                this.divisions = divisions;
            }
        }

        /*
         * Type (type) / Tied Notes
         * Fields:
         * - typeStart
         * - typeStop
         */

        public static class Type implements Serializable {
            private String typeStart;
            private String typeStop;

            public Type() {
            }

            public Type(Type type) {
            	this.typeStart = type.typeStart;
            	this.typeStop = type.typeStop;
            }

            public Type(String typeStart, String typeStop) {
            	this.typeStop = typeStart;
            	this.typeStop = typeStop;
            }

            @XmlElement(name = "type")
            public String getTypeStart() {
                return this.typeStart;
            }

            public void setTypeStart(String typeStart) {
                this.typeStart = typeStart;
            }

            @XmlElement(name = "type")
            public String getTypeStop() {
                return this.typeStop;
            }

            public void setTypeStop(String typeStop) {
                this.typeStop = typeStop;
            }
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

        @XmlElement(name = "score-part")
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

            @XmlAttribute
            public String getId() {
                return this.id;
            }

            public void setId(String id) {
                this.id = id;
            }

            @XmlElement(name = "part-name")
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