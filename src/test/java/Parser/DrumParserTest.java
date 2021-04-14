package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Parser.DrumParser.ParseDrum;


class DrumParserTest {
    @Test
    public void testParsDrum() {
        List<Output> listt = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        tab.add("CC|x---------------|--------x-------|");
        tab.add("HH|--x-x-x-x-x-x-x-|----------------|");
        tab.add("SD|----o-------o---|oooo------------|");
        tab.add("HT|----------------|----oo----------|");
        tab.add("MT|----------------|------oo--------|");
        tab.add("BD|o-------o-------|o-------o-------|");
        listt = ParseDrum(tab, listt,tab.size());
        assertTrue(listt.get(0).gettech().charAt(0)=='x');
        assertTrue(listt.get(1).gettech().charAt(0)=='o');
    }
}