package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Parser.GuitarParser.ParseGuitar;


class GuitarParserTest {
    @Test
    public void testParsGuitar() {
        List<Output> listt = new ArrayList<>();
        //List<String> tab = new ArrayList<>();
        LinkedHashMap<Integer, String> tab=new LinkedHashMap<>();

        tab.put(0,"E|--0-----------------------|-------------------------|");
        tab.put(1,"B|------------------3-----5-|-2-----------------------|");
        tab.put(2,"G|------------------3-------|-2----------2p4----------|");
        tab.put(3,"D|-------------22-----------|-2-----------------------|");
        tab.put(4,"A|--------------------------|-0-----------------------|");
        tab.put(5,"D|--------------------------|-------------------------|");

        listt = ParseGuitar(tab, listt,6);
        assertTrue(listt.get(0).getnote1()==0);
    }
}