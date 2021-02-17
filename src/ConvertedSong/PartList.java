package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class PartList implements Serializable {
    //I think we need to implement the ScorePart below as a list
    private ScorePart scorePart;

    @XmlElement(name = "score-part")
    public ScorePart getScorePart() {
        return this.scorePart;
    }

    public void setScorePart(ScorePart scorePart) {
        this.scorePart = scorePart;
    }

    public PartList() {
        scorePart = new ScorePart();
    }

    public PartList(ScorePart scorePart) {
        this.scorePart = scorePart;
    }
}
