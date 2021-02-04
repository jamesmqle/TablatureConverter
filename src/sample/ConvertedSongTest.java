package sample;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import sample.ConvertedSong;
import sample.ConvertedSong.PartList.ScorePart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConvertedSongTest {

    private JAXBContext context;

    @BeforeEach
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(ConvertedSong.class);

    }

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
		String xmlFile = "src\\sample\\ConvertedSong.xml";
		ConvertedSong song = new ConvertedSong();
		song.setTimeSignature(new ConvertedSong.TimeSignature(3,4));
		song.setPartList(new ConvertedSong.PartList(new ConvertedSong.PartList.ScorePart("Music", "P1")));
		serialize(song, xmlFile);
		deSerialize(xmlFile);
	}
}