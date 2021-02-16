package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Key implements Serializable {
    private int fifths;

    public Key() {
    }

    @XmlElement(name = "fifths")
    public int getFifths() {
        return this.fifths;
    }

    public void setFifths(int fifths) {
        this.fifths = fifths;
    }

    public Key(int fifths) {
        this.fifths = fifths;
    }
}
