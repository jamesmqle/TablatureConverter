package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;

public class Slur {

    String number;
    Type type;

    public Slur() {}
    public Slur(String number, Type type) {
        this.number = number;
        this.type = type;
    }

    @XmlElement(name = "number")
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement(name = "type")
    public Type getType() {
        return this.type;
    }
    public void setType(Type type) {
        this.type = type;
    }
}