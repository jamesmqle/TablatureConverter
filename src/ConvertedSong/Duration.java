package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Duration implements Serializable {
    private int divisions;

    public Duration() {
    }

    public Duration(int divisions) {
        this.divisions = divisions;
    }

    public Duration(ConvertedSong.ConvertedSong.Duration duration) {
        //this.divisions = duration;
        //TODO: reimplement
    }

    @XmlElement(name = "duration")
    public int getDivisions() {
        return this.divisions;
    }

    public void setDivisions(int divisions) {
        this.divisions = divisions;
    }
}
