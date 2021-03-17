package XMLTags.Common;

import XMLTags.Drums.Beam;
import XMLTags.Drums.Instrument;
import XMLTags.Drums.Unpitched;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

// Feb 17 change - Changed type from Type to String

@XmlRootElement(name = "note")
@XmlType(propOrder = {"chord", "pitch", "duration", "type"})
public class Note implements Serializable {

    private Pitch pitch;
    private int duration;
    private String type;
    private String chord;
    // added below recently - jamesmqle
    private Unpitched unpitch;
    private Instrument instrument;
    private String voice;
    private String stem;
    private String notehead;
    Beam beam1;
    Beam beam2;

    public Note() {
        this.pitch = new Pitch();
        this.duration = 4;
        //this.type = new Type();
        this.type = "whole";
    }

    public Note(Pitch g) {
        this.pitch = g;
    }

    public Note(int duration) {
        this.duration = duration;
    }

    /*public Note(Type type) {
        this.type = type;
    }
    */
    public Note(String type) {
        this.type = type;
    }

    public Note(Pitch pitch, int duration, String type) {
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
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "chord")
    public String getChord() {
        return this.chord;
    }

    public void setChord(String chord) {
        this.chord = chord;
    }

    public void chordOn(){
        this.setChord("");
    }

    /**
     * ========= DRUMS? ===========
     */
    @XmlElement(name = "unpitch")
    public void setUnpitch(Unpitched unpitch) {
        unpitch = unpitch;
    }

    public Unpitched getUnpitch() {
        return unpitch;
    }

    @XmlElement(name = "voice")
    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVoice() {
        return voice;
    }

    @XmlElement(name = "stem")
    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getStem() {
        return stem;
    }

    @XmlElement(name = "instrument")
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;

    }
    public Instrument getInstrument() {
        return instrument;
    }

    @XmlElement(name = "beam1")
    public void setBeam1(Beam beam) {
        this.beam1 = beam;
    }

    public Beam getBeam1() {
        return beam1;
    }

    @XmlElement(name = "beam2")
    public void setBeam2(Beam beam) {
        this.beam2 = beam;
    }
    public Beam getBeam2() {
        return beam2;
    }

    public Note(Unpitched pitch, int duration, String voice, String stem, String type, String notehead, Instrument instrument, Beam beam1, Beam beam2) {

        this.beam1 = beam1;
        this.beam2 = beam2;
        this.instrument = instrument;
        this.unpitch = pitch;
        this.duration = duration;
        this.voice = voice;
        this.type = type;
        this.stem = stem;
        this.voice = voice;
        this.notehead = notehead;

    }
}