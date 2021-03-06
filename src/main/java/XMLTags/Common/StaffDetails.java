package XMLTags.Common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement
@XmlType(propOrder = {"staffLines", "staffTuning"})
public class StaffDetails implements Serializable {

    private String staffLines;
    private List<StaffTuning> staffTuning;

    @XmlElement(name = "staff-lines")
    public String getStaffLines() {
        return this.staffLines;
    }

    public void setStaffLines(String staffLines) {
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
/*        this.staffTuning = new ArrayList<StaffTuning>();
        this.staffLines = 0;*/
    }

    public StaffDetails(int instrument){
        // XMLTags.Guitar
        if (instrument == 1) {
            this.staffLines = "6";
            this.staffTuning = new ArrayList<StaffTuning>();
            this.staffTuning.add(new StaffTuning("1", "E", 2));
            this.staffTuning.add(new StaffTuning("2", "A", 2));
            this.staffTuning.add(new StaffTuning("3", "D", 3));
            this.staffTuning.add(new StaffTuning("4", "G", 3));
            this.staffTuning.add(new StaffTuning("5", "B", 3));
            this.staffTuning.add(new StaffTuning("6", "E", 4));
        }
        // Bass
        else if (instrument == 2)
        {
            this.staffLines = "4";
            this.staffTuning = new ArrayList<StaffTuning>();
            this.staffTuning.add(new StaffTuning("1", "E", 1));
            this.staffTuning.add(new StaffTuning("2", "A", 1));
            this.staffTuning.add(new StaffTuning("3", "D", 2));
            this.staffTuning.add(new StaffTuning("4", "G", 2));
        }
    }

    public StaffDetails(List<StaffTuning> staffTuning){
        staffLines = Integer.toString(staffTuning.size());
        this.staffTuning = staffTuning;
    }
}
