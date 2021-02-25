package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 *  The Clef program implements attributes sign and line.
 */

@XmlType(propOrder = {"sign", "line"})
public class Clef {

    private String sign;
    private int line;

    public Clef(){}

    public Clef(String s, int l ){
        this.sign = s;
        this.line = l;
    }

    @XmlElement(name = "sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @XmlElement(name = "line")
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
