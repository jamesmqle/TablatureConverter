package ConvertedSong;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class ScorePart implements Serializable {
    //@XmlAttribute
    public static String id;
    private String partName;

    public ScorePart() {
        id = "";
        partName = "";
    }

    @XmlAttribute
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "part-name")
    public String getPartName() {
        return this.partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public ScorePart(String partName, String id) {
        this.partName = partName;
        this.id = id;
    }
}
