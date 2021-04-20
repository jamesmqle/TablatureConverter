package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Slide {

    private int number;
    private String type;

    public Slide(){
        this.number = 0;
        this.type = "";
    }

    public Slide(int number, String type){
        this.number = number;
        this.type = type;
    }

    @XmlAttribute(name = "number")
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
