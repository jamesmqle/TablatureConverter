package ConvertedSong;

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
    private Notations notations;

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
}