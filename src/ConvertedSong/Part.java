package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Part implements Serializable {
    // private Measure measure;
    private List<Measure> measures;

    public Part() {
        //this.measure = new Measure(new Attributes(new Clef(), new Key(), new TimeSignature(), 4), new Note());
        this.measures = new ArrayList<Measure>();
    }

    public void addMeasure(Measure measure) {
        this.measures.add(measure);
    }

//    @XmlElement(name = "measure")
//    public Measure getMeasure() {
//        return this.measures;
//    }
//
//    public void setMeasure(Measure measure) {
//        this.measure = measure;
//    }
}