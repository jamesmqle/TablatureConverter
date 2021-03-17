package XMLTags.Drums;

import XMLTags.Common.Part;
import XMLTags.Common.PartList;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class ScorePartwise {


    public String version;
    public PartList partList;

    public ArrayList<Part> parts;

    @XmlElement(name = "version")
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "part-list")
    public PartList getPartList() {
        return partList;
    }
    public void setPartList(PartList partList) {
        this.partList = partList;
    }

    @XmlElement(name = "parts")
    public ArrayList<Part> getParts() {
        return parts;
    }
    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }
}
