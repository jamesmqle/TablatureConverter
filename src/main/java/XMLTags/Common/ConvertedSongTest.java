package XMLTags.Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Parser.Output;
import Parser.textReader;
import XMLTags.Drums.ScoreInstrument;
import XMLTags.Guitar.Notation;
import XMLTags.Guitar.Slur;
import XMLTags.Guitar.Technical;
import XMLTags.Guitar.Tied;

public class ConvertedSongTest {
    private JAXBContext context;

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

	private static Note getLastNote(ConvertedSong song) {
		return song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).getNotes().get(song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).getNotes().size() - 1);
	}

    public static void main(String[] args) {
/*		String xmlFile = "src/main/resources/sample";
		ConvertedSong song = new ConvertedSong();

		// make song.parts not empty
		song.addPart(new Part());

		// add a note to a given measure
		song.getParts().get(0).getMeasures().get(0).addNote(new Note());

		serialize(song, xmlFile);
		deSerialize(xmlFile);*/
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
	public static String noteType(int noteDashes, int totalDashes) {
		String type;
		double quotient;

		quotient = ((double) noteDashes / (double) totalDashes);

		if (quotient == 1) type = "whole";
		else if (quotient == 0.5) type = "half";
		else if (quotient == 0.25) type = "quarter";
		else if (quotient == 0.125) type = "eighth";
		else if (quotient == 0.0625) type = "16th";
		else if (quotient == 0.03125) type = "32nd";
		else if (quotient == 0.015625) type = "64th";
		else if (quotient == 0.0078125) type = "128th";
		else type = "quarter";

		return type;
	}

	public static void initializeDrumScoreInstruments(ConvertedSong song){
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I36", "Bass Drum 1"));/*
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I37", "Bass Drum 2"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I38", "Slide Stick"));*/
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I39", "Snare"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I42", "Low Floor Tom"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I43", "Closed Hi-Hat"));/*
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I44", "High Floor Tom"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I45", "Pedal Hi-Hat"));*/
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I46", "Low Tom"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I47", "Open Hi-hat"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I48", "Low-Mid Tom"));/*
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I49", "Hi-Mid Tom"));*/
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I50", "Crash Cymbal 1"));
/*		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I51", "High Tom"));*/
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I52", "Ride Cymbal 1"));
/*		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I53", "Chinese Cymbal"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I54", "Ride Bell"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I55", "Tambourine"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I56", "Splash Cymbal"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I57", "Cowbell"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I58", "Crash Cymbal 2"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I60", "Ride Cymbal 2"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I64", "Open Hi Conga"));
		song.getPartList().getScorePart().scoreInstruments.add(new ScoreInstrument("P1-I65", "Low Conga"));*/
	}

	public static void createXML(List<Output> notes, String outputFilePath, String inputFilePath) throws FileNotFoundException {
		String xmlFile = outputFilePath;
		ConvertedSong song = new ConvertedSong();
		Part lastPart;
		Attributes attribs;
		Output prevNote = new Output();
		boolean isPerfect,  lastNoteChord = false;
		double divisionCalc, doubleNoteDur;
		int instrument, realDivisionCalc, counter = 0, noteDur, noteDashes, totalSpaces = -1, elementCounter = 0, numDD = 0;
		Output nextNote;
		Note lastNote;

		List<Output> chordNotes = new ArrayList<Output>(); // create list for all notes on same line (chord)

		instrument = textReader.detectInstrument(inputFilePath);

		// Initalize part (and first measure)
		song.addPart(new Part());
		//song.getLastPart().getMeasures().get(0).getAttributes().setStaffDetails(new StaffDetails(instrument));

		// declare staff details
		song.getLastPart().getMeasures().get(0).getAttributes().setStaffDetails(new StaffDetails(instrument));

		// Get attributes from the first measure (for subsequent measures)
		// doesnt do anything?
		attribs = song.getParts().get(0).getMeasures().get(0).getAttributes();

		// Instrument == 1: Parse as XMLTags.Guitar
		if (instrument == 1) {
			/*for (Output note: notes){
				if (note.getnote1() > 9) numDD++;
				System.out.println(note.getnote1() + " > 9: " + (note.getnote1() > 9));
				System.out.println("numDD: " + numDD);
			}*/

			if (isPowerOfTwo(textReader.numberOfDashes(inputFilePath)-numDD) && textReader.numberOfDashes(inputFilePath) > 1) {
				isPerfect = true;
			}

			else isPerfect = false;

			/**
			 * note1 corresponds to:
			 * -2 -> new tab line
			 * -1 -> new measure
			 * else -> note
			 */


			if (isPerfect) {
				System.out.println("perfect timing");
				divisionCalc = (1.0 / ((double) textReader.shortestNoteDuration(inputFilePath) /
						(double) textReader.numberOfDashes(inputFilePath))) / 4.0;
				realDivisionCalc = (int) divisionCalc;
				attribs.setDivisions(Integer.toString(realDivisionCalc));
				song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);

				// find duration of each note
				totalSpaces = realDivisionCalc * 4;
				counter = 0;
				for (Output note : notes) {
					System.out.println("Loop: " + counter);
					System.out.println("Notes Size: " + notes.size());
					if ((note.getnote1() == -1) && counter != notes.size()) {
						lastPart = song.getParts().get(song.getParts().size() - 1);
						if (counter != notes.size() - 1)
							lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(),
									Integer.toString(lastPart.getMeasures().size() + 1)));
						counter++;
					} else {
						if (counter < notes.size()) {
							nextNote = notes.get(counter + 1);
							noteDashes = nextNote.getindex() - note.getindex();
							doubleNoteDur = (((realDivisionCalc * 4.0) / (double) textReader.numberOfDashes(inputFilePath)) * noteDashes);
							noteDur = (int) doubleNoteDur;
							if (noteDur == 0) {
								chordNotes.add(note);
								lastNoteChord = true;
							} else if (noteDur != 0 && lastNoteChord) {
								chordNotes.add(note);
								lastNoteChord = false;
								elementCounter = 0;
								for (Output chordNote : chordNotes) {
									song.addNoteToMeasure(chordNote.getletter(), chordNote.getnote1());
									lastNote = getLastNote(song);
									lastNote.setDuration(noteDur);
									lastNote.setType(noteType(noteDur, totalSpaces));
									if (elementCounter == 0) {
										elementCounter++;
									} else {
										lastNote.chordOn();
									}
								}
								chordNotes = new ArrayList<Output>();
							} else {
								if (note.getnote1() == -2) ;
								else {
									song.addNoteToMeasure(note.getletter(), note.getnote1());
									lastNote = getLastNote(song);
									lastNote.setDuration(noteDur);
									lastNote.setType(noteType(noteDur, totalSpaces));
								}

							}
						} else {
							noteDur = realDivisionCalc * 4;
						}
						counter++;
					}
				}
			}

			//Test multiline imperfect input
			//imperfect input
			else {
				System.out.println("not perfect timing");
				counter = 0;
				List<Note> slurList = new ArrayList<Note>();
				//Pitch pitch, int duration, String type, int voice, Notation notations
/*
				slurList.add(new Note(new Pitch(), 2, "half", 1, new Notation(new Technical("4", "1"), new Slur("1", "start"),  new Tied("start")), new Tie("start")));
				slurList.add(new Note(new Pitch(), 1, "quarter", 1, new Notation(new Technical("4", "1"),  new Tied("start")), new Tie("start")));
				slurList.add(new Note(new Pitch(), 1, "quarter", 1, new Notation(new Technical("4", "1"), new Slur("1", "stop"),  new Tied("stop")), new Tie("stop")));
*/

				attribs.setDivisions(Integer.toString(2));
				song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);/*
				song.getParts().get(0).getMeasures().get(0).setNotes(slurList);*/
				song.getPartList().getScorePart().setPartName("Guitar 1");

				for (Output note : notes) {
					// New measure: note1 is -1
					// New tab line: note 1 is -2
					if ((note.getnote1() == -1) && counter != notes.size() - 1) { //TEST THIS: before, the getnote1 == -2 condition was commented
						// Get Part - Guitar 1
						lastPart = song.getParts().get(song.getParts().size() - 1);
						// Add measure to the part
						lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(lastPart.getMeasures().size() + 1)));
						// Set division (measure resolution)
						lastPart.getMeasures().get(lastPart.getMeasures().size() - 1).getAttributes().setDivisions("2");
						prevNote = new Output();
					}
					else if (note.getnote1() == -1 && counter == notes.size() - 1){
						// Add a barline
						song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).setBarline(new Barline("right", "light-heavy"));
					}
					else {

						// If the note is a normal note, add the note to the song
						if (counter != notes.size() - 1 && (note.getnote1() != -1 && note.getnote1() != -2)) {
							// Add a note
							song.addNoteToMeasure(note.getletter(), note.getnote1());

							// Change the note duration to a half note
							getLastNote(song).setType("half");
						}
						if (prevNote.getindex() == note.getindex())
							// if the last note is on the same index as the current note, mark it as part of a chord
							getLastNote(song).chordOn();
					}
					prevNote = note;
					counter++;
				}
			}
		}

		// Instrument == 2: Parse as Bass XMLTags.Guitar
		else if (instrument == 2){
			System.out.println("bass guitar");
			attribs.setDivisions(Integer.toString(1));
			song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
			counter = 0;
			for (Output note : notes) {
				if ((/*note.getnote1() == -2 || */note.getnote1() == -1) && counter != notes.size() - 1) { //TEST THIS: before, the getnote1 == -2 condition was commented
					lastPart = song.getParts().get(song.getParts().size() - 1);
					lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(lastPart.getMeasures().size() + 1)));/*
					lastPart.getMeasures().get(lastPart.getMeasures().size() - 1).getAttributes().setDivisions("2");*/
					prevNote = new Output();
				} else {
					if (counter != notes.size() - 1 && (note.getnote1() != -1 && note.getnote1() != -2)) {
						song.addNoteToMeasure(note.getletter(), note.getnote1());
						getLastNote(song).getPitch().setOctave(song.getLastPart().getLastMeasure().getLastNote().getPitch().getOctave()-1);
						getLastNote(song).setType("half");
					}
					if (prevNote.getindex() == note.getindex())
						getLastNote(song).chordOn();
				}
				prevNote = note;
				counter++;
			}
		}

		else if (instrument == 3){
			initializeDrumScoreInstruments(song);
			attribs.setDivisions(Integer.toString(4));
			attribs.setClef(new Clef("percussion", 2));
			song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
			counter = 0;
			song.getPartList().getScorePart().setPartName("Drum 1");

			for (Output note: notes){
				System.out.println("Marking: " + note.gettech() + "   Note 1: " + note.getnote1() + "   Letter: " + note.getletter());
			}

			for (Output note: notes){
				if ((note.getnote1() == -1) && counter != notes.size() - 1) {
					// Get last part in the song - Drum 1
					lastPart = song.getParts().get(song.getParts().size() - 1);
					// Add measure to the part
					lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), Integer.toString(lastPart.getMeasures().size() + 1)));
					// Set division (measure resolution)
					lastPart.getMeasures().get(lastPart.getMeasures().size() - 1).getAttributes().setDivisions("4");
					prevNote = new Output();
				}

				else if (note.getnote1() == -1 && counter == notes.size() - 1){
					// Add a barline
					song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).setBarline(new Barline("right", "light-heavy"));
				}

				else {
					// If the note is a normal note, add the note to the song
					if (counter != notes.size() - 1 && (note.getnote1() != -1 && note.getnote1() != -2)) {
						// Add a note TODO: add appropriate duration
						song.addDrumNoteToMeasure(note.getletter(), note.gettech(), 1);

						// Change the note duration to a half note
						getLastNote(song).setType("half");
					}
					if (prevNote.getindex() == note.getindex())
						// if the last note is on the same index as the current note, mark it as part of a chord
						getLastNote(song).chordOn();
				}
				prevNote = note;
				counter++;
			}
		}

		serialize(song, xmlFile);
		deSerialize(xmlFile);
	}
}