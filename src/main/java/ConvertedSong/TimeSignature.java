package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlType(propOrder = {"beats", "beatType"})
public class TimeSignature implements Serializable {
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
