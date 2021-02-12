package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Part implements Serializable {
    private ConvertedSong.ConvertedSong.Measure measure;

    @XmlElement(name = "measure")
    public ConvertedSong.ConvertedSong.Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(ConvertedSong.ConvertedSong.Measure measure) {
        this.measure = measure;
    }
}