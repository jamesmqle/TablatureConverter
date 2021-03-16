/*
package Parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

class textReaderTest {
    @Test
    public void testParsGuitar() {
        List<Output> listt = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        tab.add("E|--0-----------------------|-------------------------|");
        tab.add("B|------------------3-----5-|-2-----------------------|");
        tab.add("G|------------------3-------|-2-----------------------|");
        tab.add("D|------------------5-------|-2-----------------------|");
        tab.add("A|--------------------------|-0-----------------------|");
        tab.add("D|--------------------------|-------------------------|");
        listt = textReader.ParsGuitar(tab, listt);
        assertTrue(listt.get(0).getnote1()==0);
    }

    @Test
    public void testParsBass() {
        fail("Not yet implemented");
    }

    @Test
    public void testParsDrum() {
        fail("Not yet implemented");
    }

    @Test
    public void testOutput() {

        List<Output> listt = new ArrayList<>();
        assertEquals(0, listt.size());
        listt.add(new Output("e", 1, 1, "3", 1));
        listt.add(new Output("e", 1, 1, "3", 1));
        assertEquals(2, listt.size());

    }

    @Test
    public void testGetindex() {
        Output o = new Output("e", 2, 3, "4", 1);
        assertEquals(1, o.getindex());
    }

    @Test
    public void testGetnote1() {
        Output o = new Output("e", 2, 3, "4", 1);
        assertEquals(2, o.getnote1());
    }

    @Test
    public void testGetnote2() {
        Output o = new Output("e", 2, 3, "4", 1);
        assertEquals(3, o.getnote2());
    }

    @Test
    public void testGetletter() {
        Output o = new Output("e", 2, 3, "4", 1);
        assertEquals("e", o.getletter());
    }

    @Test
    public void testGettech() {
        Output o = new Output("e", 2, 3, "4", 1);
        assertEquals("4", o.gettech());
    }
}*/
