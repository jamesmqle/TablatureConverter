package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;

public class Technical {

    String string;
    String fret;

    public Technical() {}
    public Technical(String string, String fret) {
        this.string = string;
        this.fret = fret;
    }

    @XmlElement(name = "string")
    public String getString() {
        return this.string;
    }
    public void setString(String string) {
        this.string = string;
    }

    @XmlElement(name = "fret")
    public String getFret() {
        return this.fret;
    }
    public void setFret(String fret) {
        this.fret = fret;
    }
}
