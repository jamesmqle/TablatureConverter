package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;

public class Barline {

    String location;
    String barStyle;

    public Barline(){}

    public Barline(String location, String barStyle){
        this.location = location;
        this.barStyle = barStyle;
    }

    @XmlElement(name = "location")
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
