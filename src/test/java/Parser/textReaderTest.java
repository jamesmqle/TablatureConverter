package Parser;

import Parser.Output;
import Parser.textReader;
import org.junit.jupiter.api.Test;
import static Parser.textReader.TabIsOKTracker;
import static Parser.textReader.isInteger;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class textReaderTest {



    @Test
    public void testTabIsOK_guitar_1() {
        int[] arr = new int[3];
        List<String> guitartab = new ArrayList<>();
        guitartab.add("E|--0-----------------------|-------------------------|");
        guitartab.add("B|------------------3-----5-|-2----------------------|");
        guitartab.add("G|------------------3-------|-2-----------------------|");
        guitartab.add("D|------------------5-------|-2-----------------------|");
        guitartab.add("A|--------------------------|-0-----------------------|");
        guitartab.add("D|--------------------------|-------------------------|");
        arr=TabIsOKTracker(guitartab, 1);
        assertEquals(-1, arr[1]);
    }
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
    public void testTabIsOK_bass_1() {
        int[] arr = new int[3];
        List<String> basstab = new ArrayList<>();
        basstab.add("G|-----------------------------------------------|");
        basstab.add("D|-----------------------------------------------|");
        basstab.add("A|-5-55-5-5-0--4-44-4-4-0--2-22-2-2-0-----------2|");
        basstab.add("E|------------------------------------3-33-3-5--|");
        arr=TabIsOKTracker(basstab, 2);
        assertEquals(-1, arr[1]);
    }

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
    public void testTabIsOK_drum_1() {
        int[] arr = new int[3];
        List<String> drumtab = new ArrayList<>();
        drumtab.add("CC|x---------------|--------x-------|");
        drumtab.add("HH|--x-x-x-x-x-x-x-|--------------|");
        drumtab.add("SD|----o-------o---|oooo------------|");
        drumtab.add("HT|----------------|----oo----------|");
        drumtab.add("MT|----------------|------oo--------|");
        drumtab.add("BD|o-------o-------|o-------o-------|");
        arr=TabIsOKTracker(drumtab, 3);
        assertEquals(-1, arr[1]);
    }

    @Test
    public void testTabIsOK_unknown() {
        int[] arr = new int[3];
        List<String> tab = new ArrayList<>();
        tab.add("C|p---------------|--------1-------|");
        arr=TabIsOKTracker(tab, 4);
        assertEquals(10, arr[0]);
    }

    @Test
    public void testIsInteger() {
        String s = "2";
        boolean b = isInteger(s);
        assertTrue(b==true);
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


}
