package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Parser.DrumParser.ParseDrum;


class DrumParserTest {
    @Test
    public void testParsDrum() {
        List<Output> listt = new ArrayList<>();
        //List<String> tab = new ArrayList<>();
        LinkedHashMap<Integer, String> tab=new LinkedHashMap<>();

        tab.put(6,"CC|x---------------|--------x-------|");
        tab.put(6,"HH|--x-x-x-x-x-x-x-|----------------|");
        tab.put(6,"SD|----o-------o---|oooo------------|");
        tab.put(6,"HT|----------------|----oo----------|");
        tab.put(6,"MT|----------------|------oo--------|");
        tab.put(6,"BD|o-------o-------|o-------o-------|");

//        tab.add("CC|x---------------|--------x-------|");
//        tab.add("HH|--x-x-x-x-x-x-x-|----------------|");
//        tab.add("SD|----o-------o---|oooo------------|");
//        tab.add("HT|----------------|----oo----------|");
//        tab.add("MT|----------------|------oo--------|");
//        tab.add("BD|o-------o-------|o-------o-------|");
        listt = ParseDrum(tab, listt,tab.size());
        assertTrue(listt.get(0).getTech().charAt(0)=='x');
        assertTrue(listt.get(1).getTech().charAt(0)=='o');
    }
}