package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"tuningStep", "tuningOctave"})
public class StaffTuning implements Serializable {
    //I think we need to implement the ScorePart below as a list

    private String line;
    private String tuningStep;
    private int tuningOctave;

    @XmlAttribute
    public String getLine() {
        return this.line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @XmlElement(name = "tuning-octave")
    public int getTuningOctave() {
        return this.tuningOctave;
    }

    public void setTuningOctave(int tuningOctave) {
        this.tuningOctave = tuningOctave;
    }

    @XmlElement(name = "tuning-step")
    public String getTuningStep() {
        return this.tuningStep;
    }

    public void setTuningStep(String tuningStep) {
        this.tuningStep = tuningStep;
    }

    //constructors - default must fully intialize the object for testing
    public StaffTuning(){
        line = "1";
        tuningStep = "E";
        tuningOctave = 2;
    }
    public StaffTuning(String line, String tuningStep, int tuningOctave){
        this.line = line;
        this.tuningStep = tuningStep;
        this.tuningOctave = tuningOctave;
    }

}
