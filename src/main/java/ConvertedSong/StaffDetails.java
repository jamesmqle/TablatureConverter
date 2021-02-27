package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

public class StaffDetails implements Serializable {
    //I think we need to implement the ScorePart below as a list
    private int staffLines;
    private List<StaffTuning> staffTuning;

    @XmlElement(name = "staff-lines")
    public int getStaffLines() {
        return this.staffLines;
    }

    public void setScorePart(int staffLines) {
        this.staffLines = staffLines;
    }

    @XmlElement(name = "staff-tuning")
    public List<StaffTuning> getStaffTuning() {
        return this.staffTuning;
    }

    public void setStaffTuning(List<StaffTuning> staffTuning) {
        this.staffTuning = staffTuning;
    }

    //constructors - default must fully intialize the object for testing
}
