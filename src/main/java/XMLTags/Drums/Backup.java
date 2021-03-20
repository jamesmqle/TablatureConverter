package XMLTags.Drums;

import javax.xml.bind.annotation.XmlElement;

public class Backup {

    String duration;

    public Backup() {}
    public Backup(String duration) {
        this.duration = duration;

    }

    @XmlElement(name = "duration")
    public void setDuration(String duration) { this.duration = duration; }
    public String getDuration() { return duration; }

}
