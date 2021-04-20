package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Parser.BassParser.ParseBass;


class BassParserTest {
    @Test
    public void testParsBass() {
        List<Output> listt = new ArrayList<>();
       // List<String> tab = new ArrayList<>();
        LinkedHashMap<Integer, String> tab=new LinkedHashMap<>();
        tab.put(4,"G|---------------------|---------------------|");
        tab.put(4,"D|---------------------|-----------(0)-------|");
        tab.put(4,"A|-5-55-5-5--44-4-4h0--|-22-2-2-0-----------2|");
        tab.put(4,"E|---------------------|-----------3-33-3-5--|");

//        tab.add("G|---------------------|---------------------|");
//        tab.add("D|---------------------|-----------(0)-------|");
//        tab.add("A|-5-55-5-5--44-4-4h0--|-22-2-2-0-----------2|");
//        tab.add("E|---------------------|-----------3-33-3-5--|");

        listt = ParseBass(tab, listt,4);
        assertTrue(listt.get(1).getnote1()==55);
    }
}