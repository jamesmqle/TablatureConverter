package XMLTags.Common;

import Parser.NoteConvert;
import org.junit.jupiter.api.Test;
import XMLTags.Common.ConvertedSongTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import Parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConvertedSongTestTester {
    @Test
    void testGetLastNote1(){
        ConvertedSong testSong = new ConvertedSong();
        Note expectedNote = new Note();
        Note actualNote;

        expectedNote.setPitch(new Pitch(NoteConvert.intToStep(3), 2, 0));

        testSong.getParts().add(new Part());
        testSong.getParts().get(0).getMeasures().get(0).getNotes().add(expectedNote);
        testSong.getParts().get(0).getMeasures().get(0).getNotes().add(expectedNote);
        testSong.getParts().get(0).getMeasures().get(0).getNotes().add(expectedNote);
        List<Note> notes = testSong.getParts().get(0).getMeasures().get(0).getNotes();

        for (Note note : notes){
            System.out.println("parts size: " + testSong.getParts().size());
            System.out.println("measures size: " + testSong.getParts().get(0).getMeasures().size());
            System.out.println(note.getType());
        }

        actualNote = ConvertedSongTest.getLastNote(testSong);
        assertEquals(expectedNote, actualNote);
    }

    @Test
    void testGetLastNote2(){
        ConvertedSong testSong = new ConvertedSong();
        Note expectedNote;
        Note actualNote;

        testSong.getParts().add(new Part());
        testSong.addNoteToMeasure("E", 2);
        testSong.addNoteToMeasure("A", 3);
        testSong.addNoteToMeasure("D", 5);
        testSong.addNoteToMeasure("E", 1);
        testSong.addNoteToMeasure("D", 0);

        expectedNote = testSong.getParts().get(0).getMeasures().get(0).getNotes().get(testSong.getParts().get(0).getMeasures().get(0).getNotes().size()-1);
        actualNote = ConvertedSongTest.getLastNote(testSong);

        assertEquals(expectedNote, actualNote);
    }

    @Test
    void testGetLastNote3(){
        ConvertedSong testSong = new ConvertedSong();
        Note expectedNote = new Note();
        Note actualNote = new Note();

        testSong.getParts().add(new Part());
        testSong.addNoteToMeasure("E", 2);
        testSong.addNoteToMeasure("A", 3);
        testSong.addNoteToMeasure("D", 7);
        testSong.addNoteToMeasure("E", 12);
        testSong.addNoteToMeasure("D", 3);
        testSong.addNoteToMeasure("E", 25);
        testSong.addNoteToMeasure("A", 21);
        testSong.addNoteToMeasure("D", 11);
        testSong.addNoteToMeasure("E", 24);
        testSong.addNoteToMeasure("D", 11);
        testSong.addNoteToMeasure("E", 10);
        testSong.addNoteToMeasure("A", 3);
        testSong.addNoteToMeasure("D", 5);
        testSong.addNoteToMeasure("E", 3);
        testSong.addNoteToMeasure("D", 5);
        testSong.addNoteToMeasure("E", 2);
        testSong.addNoteToMeasure("A", 3);
        testSong.addNoteToMeasure("D", 5);
        testSong.addNoteToMeasure("E", 1);
        testSong.addNoteToMeasure("D", 0);

        expectedNote = testSong.getParts().get(0).getMeasures().get(0).getNotes().get(testSong.getParts().get(0).getMeasures().get(0).getNotes().size()-1);
        actualNote = ConvertedSongTest.getLastNote(testSong);

        assertEquals(expectedNote, actualNote);
    }

    @Test
    void testNoteType21(){
        String expectedType = "quarter";
        String actualType = ConvertedSongTest.noteType2(1,4);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testNoteType22(){
        String expectedType = "16th";
        String actualType = ConvertedSongTest.noteType2(1,16);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testNoteType23(){
        String expectedType = "32nd";
        String actualType = ConvertedSongTest.noteType2(1,32);
        assertEquals(expectedType, actualType);
    }

    @Test
    void testNoteType1(){
        String[] expectedType = { "whole", "half", "quarter", "eighth", "16th", "32nd", "64th", "128th"};
        String actualType;
        int tempInt = 1;

        for (String expectedVal : expectedType){
            actualType = ConvertedSongTest.noteType(4, tempInt);
            assertEquals(expectedVal, actualType);
            tempInt = tempInt * 2;
        }
    }

    @Test
    void testNoteType2(){
        String[] expectedType = {"whole", "half", "quarter", "eighth", "16th", "32nd", "64th", "128th"};
        String actualType;
        int tempInt = 1;

        for (String expectedVal : expectedType){
            actualType = ConvertedSongTest.noteType2(1, tempInt);
            assertEquals(expectedVal, actualType);
            tempInt = tempInt * 2;
        }
    }

    @Test
    void testCreateXML1() throws IOException {
        String file1 = "src/test/java/XMLTags/Common/Test 1 (Hot Cross Buns).txt";
        String actualOut = "src/test/java/XMLTags/Common/testCreateXML1out.txt";
        String expectedOut = "src/test/java/XMLTags/Common/testCreateXML1Expected.txt";
        File actualFile = new File(actualOut);
        File expectedFile = new File(expectedOut);
        String actualType;
        int tempInt = 1;

        ConvertedSongTest.createXML(Parser.textReader.readTabFile(file1), actualOut, file1);

        List<String> file1Lines = Files.readAllLines(Paths.get(actualOut));
        List<String> file2Lines = Files.readAllLines(Paths.get(expectedOut));

        byte[] file1Bytes = Files.readAllBytes(Paths.get(actualOut));
        byte[] file2Bytes = Files.readAllBytes(Paths.get(expectedOut));

        String actualStrings = new String(file1Bytes, StandardCharsets.UTF_8);
        String expectedStrings = new String(file2Bytes, StandardCharsets.UTF_8);

        assertEquals(actualStrings, expectedStrings);
    }

}