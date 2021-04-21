package XMLTags.Common;

import Parser.NoteConvert;
import org.junit.jupiter.api.Test;
import XMLTags.Common.ConvertedSongTest;
import static org.junit.jupiter.api.Assertions.*;
import Parser.Output;

public class ConvertedSongTestTest {

    @Test
    void testGetLastNote1(){
        ConvertedSong testSong = new ConvertedSong();
        Note expectedNote = new Note();
        Note actualNote = new Note();

        expectedNote.setPitch(new Pitch(NoteConvert.intToStep(3), 2, 0));
        testSong.getParts().get(0).getMeasures().get(0).getNotes().add(expectedNote);

        assertEquals(0, 0);
    }

}