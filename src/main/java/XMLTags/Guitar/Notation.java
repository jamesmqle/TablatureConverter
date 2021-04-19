package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class Notation {
    Technical technical;
    Slur slur;
    Tied tied;

    public Notation(){}

    public Notation(Technical technical){
        this.technical = technical;
    }

    public Notation(Technical technical, Slur slur){
        this.technical = technical;
        this.slur = slur;
    }

    public Notation(Technical technical, Tied tied){
        this.technical = technical;
        this.tied = tied;
    }

    public Notation(Technical technical, Slur slur, Tied tied){
        this.technical = technical;
        this.slur = slur;
        this.tied = tied;
    }

    public Notation(Slur slur, Tied tied){
        this.slur = slur;
        this.tied = tied;
    }

    @XmlElement(name = "technical")
    public Technical getTechnical(){
        return technical;
    }
    public void setTechnical(Technical technical){
        this.technical = technical;
    }

    @XmlElement(name = "slur")
    public Slur getSlur(){
        return slur;
    }
    public void setSlur(Slur slur){
        this.slur = slur;
    }

    @XmlElement(name = "tied")
    public Tied getTied(){
        return tied;
    }
    public void setTied(Tied tied){
        this.tied = tied;
    }

}
