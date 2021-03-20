package XMLTags.Common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 *  Objects: clef, key, time
 *  Attributes: divisions
 */

@XmlType(propOrder = {"divisions", "key", "time", "clef", "staffDetails"})
public class Attributes {

    private Clef clef;
    private Key key;
    private TimeSignature time;
    private String divisions;
    private StaffDetails staffDetails;


    public Attributes(){ }

    public Attributes(Clef clef, Key key, TimeSignature time, String divisions, StaffDetails staffDetails){
        this.clef = clef;
        this.key = key;
        this.time = time;
        this.divisions = divisions;
        this.staffDetails = staffDetails;
    }

    @XmlElement(name = "clef")
    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    @XmlElement(name = "key")
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @XmlElement(name = "time")
    public TimeSignature getTime() {
        return time;
    }

    public void setTime(TimeSignature time) {
        this.time = time;
    }

    @XmlElement(name = "divisions")
    public String getDivisions() {
        return divisions;
    }

    public void setDivisions(String divisions) {
        this.divisions = divisions;
    }

    @XmlElement(name = "staff-details")
    public StaffDetails getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(StaffDetails staffDetails) {
        this.staffDetails = staffDetails;
    }
}
