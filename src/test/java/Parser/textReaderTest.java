package Parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
        listt = textReader.ParseGuitar(tab, listt);
        assertTrue(listt.get(0).getnote1()==0);
    }

    @Test
    public void testTabIsOK_guitar_1() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            int flag=1;         //Its guitar
            List<String> guitartab = new ArrayList<>();
            guitartab.add("E|--0-----------------------|-------------------------|");
            guitartab.add("B|------------------3-----5-|-2----------------------|");
            guitartab.add("G|------------------3-------|-2-----------------------|");
            guitartab.add("D|------------------5-------|-2-----------------------|");
            guitartab.add("A|--------------------------|-0-----------------------|");
            guitartab.add("D|--------------------------|-------------------------|");
            textReader.TabIsOK(guitartab, flag);
        });
    }

    @Test
    public void testTabIsOK_guitar_2() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            int flag=1;         //Its guitar
            List<String> guitartab = new ArrayList<>();
            guitartab.add("E|--0-----------------------|-------------------------|");
            guitartab.add("B|------------------3-----5-|-2-----------------------|");
            guitartab.add("G|------------------3-------|-2-----------------------|");
            guitartab.add("P|------------------5-------|-2-----------------------|");
            guitartab.add("A|--------------------------|-0-----------------------|");
            guitartab.add("D|--------------------------|-------------------------|");
            textReader.TabIsOK(guitartab, flag);
        });
    }


//    @Test
//    public void testParsBass() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testParsDrum() {
//        fail("Not yet implemented");
//    }

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
}
