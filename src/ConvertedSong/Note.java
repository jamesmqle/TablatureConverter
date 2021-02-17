package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Note implements Serializable {
    private Pitch pitch;
    private int duration;
    private Type type;

    public Note() {
        this.pitch = new Pitch();
        this.duration = 0;
        this.type = new Type();
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

}