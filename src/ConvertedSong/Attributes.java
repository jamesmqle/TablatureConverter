package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;

public class Attributes {

    private Clef clef;
    private Key key;
    private TimeSignature time;
    private int divisions;

    public Attributes(Clef c, Key k , TimeSignature t, int d){
        this.clef = c;
        this.key = k;
        this.time = t;
        this.divisions = d;
    }

    @XmlElement(name = "Clef")
    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    @XmlElement(name = "Key")
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @XmlElement(name = "Time")
    public TimeSignature getTime() {
        return time;
    }

    public void setTime(TimeSignature time) {
        this.time = time;
    }

    @XmlElement(name = "Divisions")
    public int getDivisions() {
        return divisions;
    }

    public void setDivisions(int divisions) {
        this.divisions = divisions;
    }
}
