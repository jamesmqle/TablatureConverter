package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;

public class Part implements Serializable {
    private Measure measure;

    public Part() {
        this.measure = new Measure();
    }

    @XmlElement(name = "measure")
    public Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}