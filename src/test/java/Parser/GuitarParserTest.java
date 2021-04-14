package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static Parser.GuitarParser.ParseGuitar;


class GuitarParserTest {
    @Test
    public void testParsGuitar() {
        List<Output> listt = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        tab.add("E|--0-----------------------|-------------------------|");
        tab.add("B|------------------3-----5-|-2-----------------------|");
        tab.add("G|------------------3-------|-2----------2p4----------|");
        tab.add("D|-------------22-----------|-2-----------------------|");
        tab.add("A|--------------------------|-0-----------------------|");
        tab.add("D|--------------------------|-------------------------|");
        listt = ParseGuitar(tab, listt,6);
        assertTrue(listt.get(0).getnote1()==0);
    }
}