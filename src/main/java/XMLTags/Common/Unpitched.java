package XMLTags.Common;

public class Unpitched {
    String displayStep;
    String displayOctave;

    public Unpitched(){
    }

    public Unpitched(String displayStep, String displayOctave){
        this.displayOctave = displayOctave;
        this.displayStep = displayStep;
    }

    public String getDisplayOctave() {
        return displayOctave;
    }
}
