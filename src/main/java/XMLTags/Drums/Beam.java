package XMLTags.Drums;

import javax.xml.bind.annotation.XmlElement;

public class Beam {

    String number;
    String value;

    @XmlElement(name = "value")
    public String getValue() { return value; }
    public void setValue(String value) {this.value = value; }


    @XmlElement(name = "number")
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
}
