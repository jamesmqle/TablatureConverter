package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;

public class PullOff {

    private int number;
    private String type;
    private String symbol;

    @XmlAttribute(name = "number")
    public int getNumber() {
        return number;
    }
    public void setNumber(int n) {
        number = n;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setSymbol(String sym) {
        symbol = sym;
    }
    public String getSymbol() {
        return symbol;
    }
}
