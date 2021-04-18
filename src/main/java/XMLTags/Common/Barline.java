package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "barline")
public class Barline implements Serializable {

    String location;
    String barStyle;

    Repeat repeat;
    Ending ending;

    public Barline(){}

    public Barline(String location, String barStyle){
        this.location = location;
        this.barStyle = barStyle;
    }

    @XmlElement(name = "repeat")
    public Repeat getRepeat() {
        return repeat;
    }
    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    @XmlElement(name = "ending")
    public Ending getEnding() {
        return ending;
    }
    public void setEnding(Ending ending) {
        this.ending = ending;
    }

    @XmlAttribute
    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    @XmlElement(name = "bar-style")
    public String getBarStyle(){
        return this.barStyle;
    }

    public void setBarStyle(String barStyle){
        this.barStyle = barStyle;
    }
}
