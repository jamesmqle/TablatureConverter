package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement
@XmlType(propOrder = {"staffLines", "staffTuning"})
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

    public StaffDetails(){
        staffLines = 6;
        staffTuning = new ArrayList<StaffTuning>();
        staffTuning.add(new StaffTuning("1", "E", 2));
        staffTuning.add(new StaffTuning("2", "A", 2));
        staffTuning.add(new StaffTuning("3", "D", 3));
        staffTuning.add(new StaffTuning("4", "G", 3));
        staffTuning.add(new StaffTuning("5", "B", 3));
        staffTuning.add(new StaffTuning("6", "E", 4));
    }
}
