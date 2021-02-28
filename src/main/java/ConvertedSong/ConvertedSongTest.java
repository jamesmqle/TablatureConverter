package ConvertedSong;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Parser.Output;
//import org.junit.jupiter.api.BeforeEach;

public class ConvertedSongTest {
    private JAXBContext context;

/*    @BeforeEach
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(ConvertedSong.class);
    }*/

/*    @Test
    public void serialization() throws JAXBException {
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new ConvertedSong("Hot Cross Buns", 100 ) , new File("ConvertedSong.xml"));

        Unmarshaller unmarshaller = this.context.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(new File("ConvertedSong.xml"));
        System.out.println("unmarshalled = " + unmarshalled);
    }
*/

	private static void serialize(ConvertedSong song, String xmlFile) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ConvertedSong.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Display result to console
			marshaller.marshal(song, System.out);
			// Save result to xml file
			marshaller.marshal(song, new File(xmlFile));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void deSerialize(String xmlFile) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ConvertedSong.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ConvertedSong song = (ConvertedSong) unmarshaller.unmarshal(new File(xmlFile));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

    public static void main(String[] args) {
		String xmlFile = "src/main/resources/sample";
		ConvertedSong song = new ConvertedSong();
		// song.setKey((new ConvertedSong.Key(0)));

		// make song.parts not empty
		song.addPart(new Part());

		// add a note to a given measure
		// hierarchy is song.part.measures.notes
		song.getParts().get(0).getMeasures().get(0).addNote(new Note());

		// previous makeshift tests
//		song.addMeasure(new Measure());
//		song.addMeasure(new Measure());
//		song.addMeasure(new Measure());
//		song.addMeasure(new Measure());

//		song.addNote(new Note());
//		song.addNote(new Note());
//		song.addNote(new Note());
//		song.addNote(new Note());

		serialize(song, xmlFile);
		deSerialize(xmlFile);
	}

	public static void createXML(List<Output> notes, String filePath){
		String xmlFile = filePath;
		ConvertedSong song = new ConvertedSong();
		Part latestPart;
		Attributes attribs;
		Output prevNote = new Output();
		// song.setKey((new ConvertedSong.Key(0)));

		// Initalize part (and first measure)
		song.addPart(new Part());
		// Get attributes from the first measure (for subsequent measures)
		attribs = song.getParts().get(0).getMeasures().get(0).getAttributes();

		/**
		 * note1 corresponds to:
		 * -2 -> new tab line
		 * -1 -> new measure
		 * else -> note
		 */
		for (Output note : notes) {
			if (note.getnote1() == -2 || note.getnote1() == -1){
				latestPart = song.getParts().get(song.getParts().size()-1);
				latestPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(latestPart.getMeasures().size()+1)));
				prevNote = new Output();
			}
			else {
				song.addNoteToMeasure(note.getletter(), note.getnote1());
				if (prevNote.getindex() == note.getindex())
					song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().get(song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1).chordOn();
				prevNote = note;
			}
		}

		serialize(song, xmlFile);
		deSerialize(xmlFile);
	}
}