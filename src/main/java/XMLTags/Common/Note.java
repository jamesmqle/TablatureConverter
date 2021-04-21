package XMLTags.Common;

import XMLTags.Guitar.Grace;
import XMLTags.Guitar.Notation;
import XMLTags.Drums.Instrument;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

// Feb 17 change - Changed type from Type to String

@XmlRootElement(name = "note")
@XmlType(propOrder = {"grace","chord", "unpitched", "pitch", "duration", "tie", "instrument", "voice", "type", "notehead", "notations"})
public class Note implements Serializable {
    private Grace grace;
    private String notehead;
    private Pitch pitch;
    private int duration;
    private String type;
    private Chord chord;
    private int voice;
    Notation notations;
    Tie tie;
    Unpitched unpitched;
    Instrument instrument;

    public Note() {
        this.duration = 4;
        this.type = "whole";
        this.voice = 1;
    }

    public Note(Pitch pitch, int duration, String type) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = 1;
    }

    @XmlElement(name = "notehead")
    public String getNotehead() {
        return notehead;
    }

    public void setNotehead(String notehead) {
        this.notehead = notehead;
    }

    public Note(Pitch pitch, int duration, String type, int voice) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = voice;
    }

    public Note(Pitch pitch, int duration, String type, int voice, Notation notations) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = voice;
        this.notations = notations;
    }

    public Note(Pitch pitch, int duration, String type, int voice, Notation notations, Tie tie) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = voice;
        this.notations = notations;
        this.tie = tie;
    }

    public Note(Pitch pitch, int duration, String type, int voice, Notation notations, Tie tie, Instrument instrument) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = voice;
        this.notations = notations;
        this.tie = tie;
        this.instrument = instrument;
    }

    @XmlElement(name = "grace")
    public Grace getGrace() {
        return grace;
    }
    public void setGrace(Grace grace) {
        this.grace = grace;
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
    public Chord getChord() {
        return this.chord;
    }

    public void setChord(Chord chord) {
        this.chord = chord;
    }

    public void chordOn(){
        this.setChord(new Chord());
    }

    @XmlElement(name = "notations")
    public Notation getNotations() {
        return this.notations;
    }

    public void setNotations(Notation notations) {
        this.notations = notations;
    }

    @XmlElement(name = "voice")
    public int getVoice() {
        return this.voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    @XmlElement(name = "tie")
    public Tie getTie() {
        return this.tie;
    }

    public void setTie(Tie tie) {
        this.tie = tie;
    }

    @XmlElement(name = "unpitched")
    public Unpitched getUnpitched() {
        return this.unpitched;
    }

    public void setUnpitched(Unpitched unpitched) {
        this.unpitched = unpitched;
    }

    @XmlElement(name = "instrument")
    public Instrument getInstrument(){
        return this.instrument;
    }
    public void setInstrument(Instrument instrument){
        this.instrument = instrument;
    }
}