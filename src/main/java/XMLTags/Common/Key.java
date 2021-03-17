package XMLTags.Common;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Key implements Serializable {
    private int fifths;
    private String mode;

    public Key() {
    }
    public Key(int fifths, String mode) {
        this.fifths = fifths;
        this.mode = mode;
    }

    @XmlElement(name = "fifths")
    public int getFifths() {
        return this.fifths;
    }

    public void setFifths(int fifths) {
        this.fifths = fifths;
    }

    @XmlElement(name = "mode")
    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
