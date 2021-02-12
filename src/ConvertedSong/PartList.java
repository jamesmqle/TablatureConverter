package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class PartList implements Serializable {
    //I think we need to implement the ScorePart below as a list
    private ConvertedSong.ConvertedSong.ScorePart scorePart;

    @XmlElement(name = "score-part")
    public ConvertedSong.ConvertedSong.ScorePart getScorePart() {
        return this.scorePart;
    }

    public void setScorePart(ConvertedSong.ConvertedSong.ScorePart scorePart) {
        this.scorePart = scorePart;
    }

    public PartList() {
    }

    public PartList(ConvertedSong.ConvertedSong.ScorePart scorePart) {
        this.scorePart = scorePart;
    }
}
