package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;


/*
 *  Objects: clef, key, time
 *  Attributes: divisions
 */
public class Attributes {

    private Clef clef;
    private Key key;
    private TimeSignature time;
    private int divisions;

    public Attributes(){
        this.clef = new Clef("a",1);
        this.key = new Key(1);
        this.time = new TimeSignature(2,2);
        this.divisions = -1;
    }

    /*
     *
     public Attributes(){
        this.clef = new Clef();
        this.key = new Key();
        this.time = new TimeSignature();
        this.divisions = -1;
    }
     */

    public Attributes(Clef c, Key k , TimeSignature t, int d){
        this.clef = c;
        this.key = k;
        this.time = t;
        this.divisions = d;
    }

    @XmlElement(name = "clef")
    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    @XmlElement(name = "key")
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @XmlElement(name = "time")
    public TimeSignature getTime() {
        return time;
    }

    public void setTime(TimeSignature time) {
        this.time = time;
    }

    @XmlElement(name = "divisions")
    public int getDivisions() {
        return divisions;
    }

    public void setDivisions(int divisions) {
        this.divisions = divisions;
    }
}
