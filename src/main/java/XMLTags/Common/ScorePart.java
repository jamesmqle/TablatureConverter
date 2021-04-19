package XMLTags.Common;

import XMLTags.Drums.ScoreInstrument;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScorePart implements Serializable {

    public static String id;
    private String partName;
    ArrayList<ScoreInstrument> scoreInstruments; // FOR  DRUMS

    public ScorePart() {
        id = "";
        partName = "";
    }

    public ScorePart(String partName, String id) {
        this.partName = partName;
        this.id = id;
        scoreInstruments = new ArrayList<ScoreInstrument>();
    }

    public ScorePart(String partName, String id, ArrayList<ScoreInstrument> scoreInstruments) {
        this.partName = partName;
        this.id = id;
        this.scoreInstruments = scoreInstruments;
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

    @XmlElement(name = "score-instrument")
    public ArrayList<ScoreInstrument> getScoreInstruments() {
        return this.scoreInstruments;
    }

    public void setScoreInstrument(ArrayList<ScoreInstrument> scoreInstruments) {
        this.scoreInstruments = scoreInstruments;
    }
}
