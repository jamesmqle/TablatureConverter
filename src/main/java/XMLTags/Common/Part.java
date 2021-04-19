package XMLTags.Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "part")
public class Part implements Serializable {
    // private Measure measure;
    //@XmlAttribute
    private String id;
    private List<Measure> measures;

    public Part() {
        this.measures = new ArrayList<Measure>();
        this.measures.add(new Measure()); //this.measure = new Measure(new Attributes(new Clef(), new Key(), new TimeSignature(), 4), new Note());
        this.id = "G1";
    }

    public Part(int instrument) {
        if (instrument == 1){
            this.measures = new ArrayList<Measure>();
            this.measures.add(new Measure()); //this.measure = new Measure(new Attributes(new Clef(), new Key(), new TimeSignature(), 4), new Note());
            this.id = "G1";
        }
        else if (instrument == 2){
            this.measures = new ArrayList<Measure>();
            this.measures.add(new Measure()); //this.measure = new Measure(new Attributes(new Clef(), new Key(), new TimeSignature(), 4), new Note());
            this.id = "B1";
        }
        else if (instrument == 3){
            this.measures = new ArrayList<Measure>();
            this.measures.add(new Measure()); //this.measure = new Measure(new Attributes(new Clef(), new Key(), new TimeSignature(), 4), new Note());
            this.id = "D1";
        }
    }

    public Part(List<Measure> measures) {
        this.measures = measures;
    }

    @XmlElement(name = "measure")
    public List<Measure> getMeasures(){
        return this.measures;
    }

    public void addMeasure(Measure measure) {
        this.measures.add(measure);
    }

    public Measure getLastMeasure(){
        return this.measures.get(this.measures.size()-1);
    }

    @XmlAttribute
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}