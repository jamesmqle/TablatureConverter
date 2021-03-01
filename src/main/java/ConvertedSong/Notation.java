package ConvertedSong;

import javax.xml.bind.annotation.XmlElement;

public class Notation {
    Technical technical;

    public Notation(){}

    public Notation(Technical technical){
        this.technical = technical;
    }

    @XmlElement(name = "technical")
    public Technical getTechnical(){
        return technical;
    }

    public void setTechnical(Technical technical){
        this.technical = technical;
    }
}
