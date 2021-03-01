package ConvertedSong;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Parser.Output;
import Parser.textReader;
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

	public static boolean isPowerOfTwo(int num){
		return (int)(Math.ceil((Math.log(num) / Math.log(2))))
				== (int)(Math.floor(((Math.log(num) / Math.log(2)))));
	}

	/**
	 * This method will determine the note type
	 * @param noteDashes
	 * @param totalDashes
	 * @return
	 */
	public static String noteType(int noteDashes, int totalDashes){
		String type;
		double quotient;

		quotient = ((double)noteDashes/(double)totalDashes);

		if (quotient == 1) type = "whole";
		else if (quotient == 0.5) type = "half";
		else if (quotient == 0.25) type = "quarter";
		else if (quotient == 0.125) type = "eighth";
		else type = "sixteenth";

		return type;
	}

	public static void createXML(List<Output> notes, String filePath, String inputFilePath) throws FileNotFoundException {
		String xmlFile = filePath;
		ConvertedSong song = new ConvertedSong();
		Part lastPart;
		Attributes attribs;
		Output prevNote = new Output();
		boolean isPerfect;
		double divisionCalc;
		double doubleNoteDur;
		int realDivisionCalc;
		int counter = 0;
		int noteDur, noteDashes;
		Output nextNote;
		boolean lastNoteChord = false;
		boolean detectLast = false;
		Note lastNote;
		Measure lastMeasure;
		int totalSpaces = -1;

		// song.setKey((new ConvertedSong.Key(0)));
		List<Output> chordNotes = new ArrayList<Output>(); // create list for all notes on same line (chord)

		if (isPowerOfTwo(textReader.numberOfDashes(inputFilePath)) && textReader.numberOfDashes(inputFilePath) > 1) {
			System.out.println("NUMBER OF DASHES:" + textReader.numberOfDashes(inputFilePath));
			isPerfect = true;
		}
		else isPerfect = false;

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

		if (isPerfect){
			System.out.println("perfect timing");
			divisionCalc = (1.0/((double)textReader.shortestNoteDuration(inputFilePath)/(double)textReader.numberOfDashes(inputFilePath)))/4.0;
			realDivisionCalc = (int) divisionCalc;
			attribs.setDivisions(Integer.toString(realDivisionCalc));
			// find length of each note
			totalSpaces = realDivisionCalc * 4;
			counter = 0;
			for (Output note : notes){
				System.out.println("Loop: " + counter);
				System.out.println("Notes Size: " + notes.size());
				if (( note.getnote1() == -1) && counter != notes.size()){
					lastPart = song.getParts().get(song.getParts().size()-1);
					if (counter != notes.size() - 1 )lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(lastPart.getMeasures().size()+1)));
					counter++;
				}
				else{
					if (counter == notes.size()){
						System.out.println("asdkjasldjlaskjdklajsdkl: " + note.getnote1());
					}
					if (counter < notes.size()) {
						nextNote = notes.get(counter + 1);
						noteDashes = nextNote.getindex() - note.getindex();
						doubleNoteDur = (((realDivisionCalc*4.0)/(double)textReader.numberOfDashes(inputFilePath))*noteDashes);
						//System.out.println("Note dashes: " + noteDashes);
						noteDur = (int) doubleNoteDur;
						//System.out.println(nextNote.getnote1());
						//System.out.println("noteDur = " + noteDur + ", lastNoteChord = " + lastNoteChord);
						if (noteDur == 0) {
							chordNotes.add(note);
							lastNoteChord = true;
						}
						else if (noteDur != 0 && lastNoteChord){
							chordNotes.add(note);
							for (Output chordNote : chordNotes){
								song.addNoteToMeasure(chordNote.getletter(), chordNote.getnote1());
						//		System.out.println("Note added: " + chordNote.getletter() + ", " + chordNote.getnote1() + ", noteDur: " + noteDur);
								lastNote = song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().get(song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1);
								lastNote.setDuration(noteDur);
								lastNote.setType(noteType(noteDur, totalSpaces));
								lastNote.chordOn();
							}
						}
						else{
							song.addNoteToMeasure(note.getletter(), note.getnote1());
							lastNote = song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().get(song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1);
							lastNote.setDuration(noteDur);
							lastNote.setType(noteType(noteDur, totalSpaces));
						}
					}
					else{
						System.out.println(note.getnote1());
						noteDur = realDivisionCalc * 4;
					}
					counter++;
				}
			}
			/*
			for(Output note : list){
            if(counter == 0) counter++;
            else{
                noteDur = note.getindex() - prevNote.getindex();
                //System.out.println(noteDur);
            }

            if(noteDur % 2 == 1 ){ // if odd then return 1
                return 1;
            }
            else if(minNoteDur > noteDur && noteDur != 0){
                minNoteDur = noteDur;
            }

            prevNote = note;
        }
        */
		}

		//imperfect input
		else{
			System.out.println("not perfect timing");
			counter = 0;
			for (Output note : notes) {
				if (/*note.getnote1() == -2 || */note.getnote1() == -1 && counter != notes.size() - 1 ){
					lastPart = song.getParts().get(song.getParts().size()-1);
					lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(lastPart.getMeasures().size()+1)));
					lastPart.getMeasures().get(lastPart.getMeasures().size()-1).getAttributes().setDivisions("2");
					prevNote = new Output();
				}
				else {
					if (counter != notes.size() - 1 && (note.getnote1() != -1 && note.getnote1() != -2)) {
						song.addNoteToMeasure(note.getletter(), note.getnote1());
						song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().get(song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1).setType("half");
					}
					if (prevNote.getindex() == note.getindex())
						song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().get(song.getParts().get(song.getParts().size()-1).getMeasures().get(song.getParts().get(song.getParts().size()-1).getMeasures().size()-1).getNotes().size()-1).chordOn();
				}
				prevNote = note;
				counter++;
			}
		}

		serialize(song, xmlFile);
		deSerialize(xmlFile);
	}
}