package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Part implements Serializable {
    private Measure measure;

    @XmlElement(name = "measure")
    public Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}