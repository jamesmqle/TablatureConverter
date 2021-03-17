package XMLTags.Guitar;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Type implements Serializable {
    private String typeStart;
    private String typeStop;

    public Type() {
        this.typeStart = "";
        this.typeStop = "";
    }

    public Type(Type type) {
        this.typeStart = type.getTypeStart();
        this.typeStop = type.getTypeStop();
    }

    public Type(String typeStart, String typeStop) {
        this.typeStop = typeStart;
        this.typeStop = typeStop;
    }

    @XmlElement(name = "type-start")
    public String getTypeStart() {
        return this.typeStart;
    }

    public void setTypeStart(String typeStart) {
        this.typeStart = typeStart;
    }

    @XmlElement(name = "type-stop")
    public String getTypeStop() {
        return this.typeStop;
    }

    public void setTypeStop(String typeStop) {
        this.typeStop = typeStop;
    }
}
