package XMLTags.Common;

import XMLTags.Drums.ScoreInstrument;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;

public class ScorePart implements Serializable {

    public static String id;
    private String partName;
    ArrayList<ScoreInstrument> instruments; // FOR  DRUMS

    public ScorePart() {
        id = "";
        partName = "";
    }

    /** FOR DRUM
     *
      public ScorePart(String id, String partName, ArrayList<ScoreInstrument> instruments) {

      		super();
      		this.id = id;
      		this.partName = partName;
      		this.instruments = instruments;
     }

    public void setScoreInstrument(ArrayList<ScoreInstrument> instrument) {
        this.instruments = instruments;
    }

    public ArrayList<ScoreInstrument> getScoreInstrument() {
        return this.instruments;
    }
*/

    @XmlAttribute
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "part-name")
    public String getPartName() {
        return this.partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public ScorePart(String partName, String id) {
        this.partName = partName;
        this.id = id;
    }
}
