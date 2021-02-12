package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Pitch implements Serializable {
    private String step;
    private int octave;

    public Pitch() {
    }

    //redundant?
    public Pitch(ConvertedSong.ConvertedSong.Pitch pitch) {
        this.octave = pitch.getOctave();
        this.step = pitch.getStep();
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
