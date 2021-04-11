package Parser;

import Parser.Output;
import Parser.textReader;
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
        tab.add("G|------------------3-------|-2----------2p4----------|");
        tab.add("D|-------------22-----------|-2-----------------------|");
        tab.add("A|--------------------------|-0-----------------------|");
        tab.add("D|--------------------------|-------------------------|");
        listt = textReader.ParseGuitar(tab, listt);
        assertTrue(listt.get(0).getnote1()==0);
    }

//    @Test
//    public void testTabIsOK_guitar_1() {
//        assertThrows(StringIndexOutOfBoundsException.class, () -> {
//            int flag=1;         //Its guitar
//            List<String> guitartab = new ArrayList<>();
//            guitartab.add("E|--0-----------------------|-------------------------|");
//            guitartab.add("B|------------------3-----5-|-2----------------------|");
//            guitartab.add("G|------------------3-------|-2-----------------------|");
//            guitartab.add("D|------------------5-------|-2-----------------------|");
//            guitartab.add("A|--------------------------|-0-----------------------|");
//            guitartab.add("D|--------------------------|-------------------------|");
//            textReader.TabIsOK(guitartab, flag);
//        });
//    }
//
//    @Test
//    public void testTabIsOK_guitar_2() {
//        assertThrows(StringIndexOutOfBoundsException.class, () -> {
//            int flag=1;         //Its guitar
//            List<String> guitartab = new ArrayList<>();
//            guitartab.add("E|--0-----------------------|-------------------------|");
//            guitartab.add("B|------------------3-----5-|-2-----------------------|");
//            guitartab.add("G|------------------3-------|-2-----------------------|");
//            guitartab.add("P|------------------5-------|-2-----------------------|");
//            guitartab.add("A|--------------------------|-0-----------------------|");
//            guitartab.add("D|--------------------------|-------------------------|");
//            textReader.TabIsOK(guitartab, flag);
//        });
//    }


    @Test
    public void testParsBass() {
        List<Output> listt = new ArrayList<>();
        List<String> tab = new ArrayList<>();
        tab.add("G|---------------------|---------------------|");
        tab.add("D|---------------------|-----------(0)-------|");
        tab.add("A|-5-55-5-5--44-4-4h0--|-22-2-2-0-----------2|");
        tab.add("E|---------------------|-----------3-33-3-5--|");
        listt = textReader.ParseBass(tab, listt);
        assertTrue(listt.get(1).getnote1()==55);
    }

//    @Test
//    public void testTabIsOK_bass_1() {
//        assertThrows(StringIndexOutOfBoundsException.class, () -> {
//            int flag=2;         //Its bass
//            List<String> basstab = new ArrayList<>();
//            basstab.add("G|-----------------------------------------------|");
//            basstab.add("D|-----------------------------------------------|");
//            basstab.add("A|-5-55-5-5-0--4-44-4-4-0--2-22-2-2-0-----------2|");
//            basstab.add("E|------------------------------------3-33-3-5--|");
//            textReader.TabIsOK(basstab, flag);
//        });
//    }
//
//    @Test
//    public void testTabIsOK_bass_2() {
//        assertThrows(StringIndexOutOfBoundsException.class, () -> {
//            int flag=2;         //Its bass
//            List<String> basstab = new ArrayList<>();
//            basstab.add("G|-----------------------------------------------|");
//            basstab.add("D|-----------------------------------------------|");
//            basstab.add("A|-5-55-5-5-0--4-44-4-4-0--2-22-2-2-0-----------2|");
//            basstab.add("P|-------------------------------------3-33-3-5--|");
//            textReader.TabIsOK(basstab, flag);
//        });
//    }


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
        listt = textReader.ParseDrum(tab, listt,tab.size());
        assertTrue(listt.get(0).gettech().charAt(0)=='x');
        assertTrue(listt.get(1).gettech().charAt(0)=='o');
    }

//    @Test
//    public void testTabIsOK_drum_1() {
//        assertThrows(StringIndexOutOfBoundsException.class, () -> {
//            int flag=3;         //Its drum
//            List<String> drumtab = new ArrayList<>();
//            drumtab.add("CC|x---------------|--------x-------|");
//            drumtab.add("HH|--x-x-x-x-x-x-x-|---------------|");
//            drumtab.add("SD|----o-------o---|oooo------------|");
//            drumtab.add("HT|----------------|----oo----------|");
//            drumtab.add("MT|----------------|------oo--------|");
//            drumtab.add("BD|o-------o-------|o-------o-------|");
//            textReader.TabIsOK(drumtab, flag);
//        });
//    }

    /*
    @Test
    public void testTabIsOK_drum_2() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            int flag=3;         //Its drum
            List<String> drumtab = new ArrayList<>();
            drumtab.add("CC|x---------------|--------x-------|");
            drumtab.add("HH|--x-x-x-x-x-x-x-|----------------|");
            drumtab.add("SD|----o-------o---|oooo------------|");
            drumtab.add("HT|----------------|----oo----------|");
            drumtab.add("MT|----------------|------oo--------|");
            drumtab.add("BD|o-------o-------|o-------o-------|");
            textReader.TabIsOK(drumtab, flag);
        });
    }
    */

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
