package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Work implements Serializable {
    private String workTitle;

    public Work() {}

    public Work(String workTitle) {
        this.workTitle = workTitle;
    }

    @XmlElement(name = "work-title")
    public String getType() {
        return this.workTitle;
    }
    public void setType(String workTitle) {
        this.workTitle = workTitle;
    }
}
