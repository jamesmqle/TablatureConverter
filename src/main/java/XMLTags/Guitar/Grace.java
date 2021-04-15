package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Grace implements Serializable {

    String slash;

    public Grace(){ }
    public Grace(String slash){
        this.slash = slash;
    }

    @XmlAttribute(name = "slash")
    public String getSlash(){
        return slash;
    }
    public void setSlash(String slash){
        this.slash = slash;
    }
}
