package XMLTags.Common;

import XMLTags.Guitar.Notation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

// Feb 17 change - Changed type from Type to String

@XmlRootElement(name = "note")
@XmlType(propOrder = {"chord", "pitch", "duration", "voice", "type", "notations"})
public class Note implements Serializable {
    private Pitch pitch;
    private int duration;
    //private Type type;
    private String type;
    private String chord;
    private int voice;
    Notation notations;

    public Note() {
        this.pitch = new Pitch();
        this.duration = 4;
        //this.type = new Type();
        this.type = "whole";
        this.voice = 1;
    }

    public Note(Pitch pitch, int duration, String type) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = 1;
    }

    public Note(Pitch pitch, int duration, String type, int voice) {
        this.pitch = pitch;
        this.duration = duration;
        this.type = type;
        this.voice = voice;
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
}