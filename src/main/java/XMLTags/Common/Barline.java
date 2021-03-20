package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "barline")
public class Barline {

    private String location;
    private String barStyle;

    public Barline(){}

    public Barline(String location, String barStyle){
        this.location = location;
        this.barStyle = barStyle;
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
