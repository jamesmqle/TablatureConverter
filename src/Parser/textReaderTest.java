package Parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class textReaderTest {

	@Test
	public void testParsGuitar() {
		List<Output> listt = new ArrayList<>();
		List<String> tab = new ArrayList<>();
		tab.add("E|--0-----------------------|-------------------------|");
		tab.add("B|------------------3-----5-|-2-----------------------|");
		tab.add("G|------------------3-------|-2-----------------------|");
		tab.add("D|------------------5-------|-2p4---------------------|");
		tab.add("A|--------------------------|-0-----------------------|");
		tab.add("D|--------------------------|-------------------------|");
		listt = textReader.ParsGuitar(tab, listt);
		assertTrue(listt.get(0).getnote1() == 0);
		assertTrue(listt.get(8).getnote2() == 4);
		assertEquals("p", listt.get(8).gettech());
	}

	@Test
	public void testParsBass() {
		List<Output> listt = new ArrayList<>();
		List<String> tab = new ArrayList<>();
		tab.add("G|---------------|----------------|");
		tab.add("D|---------------|----------------|");
		tab.add("A|-5-5-6-5-5-5-5-|-5-5-5-5-5-5-5--|");
		tab.add("D|---------------|----------------|");
		listt = textReader.ParsDrum(tab, listt);
		assertTrue(listt.get(0).getletter() == "A");
		assertEquals(6, listt.get(3).getnote1());
	}

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
		listt = textReader.ParsDrum(tab, listt);
		assertTrue(listt.get(0).getletter() == "CC");
		assertEquals("x", listt.get(3).gettech());
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
