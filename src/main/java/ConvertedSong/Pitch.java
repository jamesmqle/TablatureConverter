package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"step", "alter", "octave"})
public class Pitch implements Serializable {
    private String step;
    private int octave;
    private int alter;

    public Pitch() {
        step = "C";
        octave = 4;
        alter = 0;
    }

/*    //redundant?
    public Pitch(Pitch pitch) {
        this.octave = pitch.getOctave();
        this.step = pitch.getStep();
    }*/

    public Pitch(String step, int octave, int alter) {
        this.step = step;
        this.octave = octave;
        this.alter = alter;
    }

    @XmlElement(name = "step")
    public String getStep() {
        return this.step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @XmlElement(name = "octave")
    public int getOctave() {
        return this.octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    @XmlElement(name = "alter")
    public int getAlter() {
        return this.alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
