package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class PullOff {

    private int number;
    private String type;
    private String symbol;

    public PullOff(){
        this.number = 0;
        this.type = "";
        this.symbol = "";
    }

    public PullOff(int number, String type, String symbol){
        this.number = number;
        this.type = type;
        this.symbol = symbol;
    }

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

    @XmlValue
    public void setSymbol(String sym) {
        symbol = sym;
    }
    public String getSymbol() {
        return symbol;
    }
}
