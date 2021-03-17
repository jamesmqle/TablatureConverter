package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;

public class Notation {
    Technical technical;
    Slur slur;

    public Notation(){}

    public Notation(Technical technical, Slur slur){ this.technical = technical; this.slur = slur; }

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
}
