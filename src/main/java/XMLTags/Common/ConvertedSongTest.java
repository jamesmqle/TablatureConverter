package XMLTags.Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import GUI.Controller;
import Parser.Output;
import Parser.textReader;
import XMLTags.Guitar.*;

public class ConvertedSongTest {
    private JAXBContext context;
    public static HashMap<Integer, Integer[]> MEASURE_POSITION_MAP = new HashMap<>();

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

    public static boolean isPowerOfTwo(int num) {
        return (int) (Math.ceil((Math.log(num) / Math.log(2))))
                == (int) (Math.floor(((Math.log(num) / Math.log(2)))));
    }

    /**
     * This method will determine the note type
     *
     * @param noteDuration
     * @param divisions
     * @return
     */
    public static String noteType(int noteDuration, int divisions) {
        String type;
        double quotient;

        quotient = ((double) noteDuration / (double) (divisions*4));

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

    public static void createXML(List<Output> notes, String outputFilePath, String inputFilePath) throws FileNotFoundException {

        String xmlFile = outputFilePath;
        ConvertedSong song = new ConvertedSong();
        Part lastPart;
        Attributes attribs;
        Output prevNote = new Output();
        boolean isPerfect, lastNoteChord = false;
        double divisionCalc, doubleNoteDur;
        int instrument, realDivisionCalc, counter = 0, noteDur, noteDashes, totalSpaces = -1, elementCounter = 0, numDD = 0;
        Output nextNote;
        Note lastNote;

        MEASURE_POSITION_MAP.clear();

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

            if (isPowerOfTwo(textReader.numberOfDashes(inputFilePath) - numDD) && textReader.numberOfDashes(inputFilePath) > 1) {
                isPerfect = true;
            } else isPerfect = false;

            /**
             * note1 corresponds to:
             * -2 -> new tab line
             * -1 -> new measure
             * else -> note
             */

//            isPerfect = true;
//            if (isPerfect) {
//                System.out.println("perfect timing");
//
//                Matcher matcher = Pattern.compile("[0-9]+").matcher(Controller.timeSignature);
//                matcher.find();
//                double beatCount = Integer.parseInt(matcher.group());
//                matcher.find();
//                double beatType = Integer.parseInt(matcher.group());
//
//                double totalMeasureDuration = beatCount*(1.0/beatType);
//                double shortestDurationRatio = ((double) textReader.shortestNoteDuration(inputFilePath) /
//                                        (double) textReader.numberOfDashes(inputFilePath));
//
//                divisionCalc = (1.0 / (shortestDurationRatio*totalMeasureDuration)) / 4.0;
//
//                realDivisionCalc = (int)Math.ceil(divisionCalc);
//                attribs.setDivisions(Integer.toString(realDivisionCalc));
//                song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
//
//                for (Output note : notes) {
//                    System.out.println("Loop: " + counter);
//                    System.out.println("Notes Size: " + notes.size());
//                    if ((note.getnote1() == -1) && counter != notes.size()) {
//                        lastPart = song.getParts().get(song.getParts().size() - 1);
//                        if (counter != notes.size() - 1) {
//                            int measureNum = lastPart.getMeasures().size() + 1;
//                            lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), measureNum));
//                            MEASURE_POSITION_MAP.put(measureNum, new Integer[]{note.getLine(), note.getLineCol()});
//                        }
//                    } else {
//                        if (counter < notes.size()) {
//                            nextNote = notes.get(counter + 1);
//                            noteDashes = nextNote.getindex() - note.getindex();
//                            //div = 2
//                            doubleNoteDur = ((realDivisionCalc * 4.0) * ((noteDashes/(double) textReader.numberOfDashes(inputFilePath))*totalMeasureDuration));
//                            noteDur = (int) doubleNoteDur;
//                            if (noteDur == 0) {
//                                chordNotes.add(note);
//                                lastNoteChord = true;
//                            } else if (noteDur != 0 && lastNoteChord) {
//                                chordNotes.add(note);
//                                lastNoteChord = false;
//                                elementCounter = 0;
//                                for (Output chordNote : chordNotes) {
//                                    lastPart = song.getParts().get(song.getParts().size() - 1);
//                                    int measureCount = lastPart.getMeasures().size();
//                                    if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
//                                        MEASURE_POSITION_MAP.put(1, new Integer[]{chordNote.getLine(), chordNote.getLineCol()});
//                                    }
//                                    song.addNoteToMeasure(chordNote.getletter(), chordNote.getnote1());
//                                    lastNote = getLastNote(song);
//                                    lastNote.setDuration(noteDur);
//                                    lastNote.setType(noteType(noteDur, realDivisionCalc));
//                                    if (elementCounter == 0) {
//                                        elementCounter++;
//                                    } else {
//                                        lastNote.chordOn();
//                                    }
//                                }
//                                chordNotes = new ArrayList<Output>();
//                            } else {
//                                if (note.getnote1() == -2) ;
//                                else {
//                                    lastPart = song.getParts().get(song.getParts().size() - 1);
//                                    int measureCount = lastPart.getMeasures().size();
//                                    if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
//                                        MEASURE_POSITION_MAP.put(1, new Integer[]{note.getLine(), note.getLineCol()});
//                                    }
//                                    song.addNoteToMeasure(note.getletter(), note.getnote1());
//                                    lastNote = getLastNote(song);
//                                    lastNote.setDuration(noteDur);
//                                    lastNote.setType(noteType(noteDur, totalSpaces));
//                                }
//
//                            }
//                        } else {
//                            noteDur = realDivisionCalc * 4;
//                        }
//                    }
//                    counter++;
//                }
//            }

            //Test multiline imperfect input
            //imperfect input
            //else {
            System.out.println("not perfect timing");
            counter = 0;
            List<Note> slurList = new ArrayList<Note>();
            //Pitch pitch, int duration, String type, int voice, Notation notations
/*
				slurList.add(new Note(new Pitch(), 2, "half", 1, new Notation(new Technical("4", "1"), new Slur("1", "start"),  new Tied("start")), new Tie("start")));
				slurList.add(new Note(new Pitch(), 1, "quarter", 1, new Notation(new Technical("4", "1"),  new Tied("start")), new Tie("start")));
				slurList.add(new Note(new Pitch(), 1, "quarter", 1, new Notation(new Technical("4", "1"), new Slur("1", "stop"),  new Tied("stop")), new Tie("stop")));
*/


            Matcher matcher = Pattern.compile("[0-9]+").matcher(Controller.timeSignature);
            matcher.find();
            double beatCount = Integer.parseInt(matcher.group());
            matcher.find();
            double beatType = Integer.parseInt(matcher.group());

            double totalMeasureDuration = beatCount*(1.0/beatType);
            double shortestDurationRatio = ((double) textReader.shortestNoteDuration(inputFilePath) /
                    (double) textReader.numberOfDashes(inputFilePath));

            divisionCalc = (1.0 / (shortestDurationRatio*totalMeasureDuration)) / 4.0;

            realDivisionCalc = (int)Math.ceil(divisionCalc);

            attribs.setDivisions(Integer.toString(realDivisionCalc));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);/*
				song.getParts().get(0).getMeasures().get(0).setNotes(slurList);*/
            song.getPartList().getScorePart().setPartName("Guitar 1");

            for (Output note : notes) {
                System.out.println("Note 1: " + note.getnote1());
                System.out.println("Note Size: " + notes.size());
                System.out.println("Counter: " + counter);
                // New measure: note1 is -1
                // New tab line: note 1 is -2
                if ((note.getnote1() == -1) && counter != notes.size() - 1) {
                    // Get Part - Guitar 1
                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    // Add measure to the part
                    //TODO Note: we left out the if statement here that was in the original thing
                    int measureNum = lastPart.getMeasures().size() + 1;
                    lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), measureNum));
                    MEASURE_POSITION_MAP.put(measureNum, new Integer[]{note.getLine(), note.getLineCol()});
                    // Set division (measure resolution)
                    lastPart.getMeasures().get(lastPart.getMeasures().size() - 1).getAttributes().setDivisions("2");
                    prevNote = new Output();
                } else if (note.getnote1() == -1 && counter == notes.size() - 1) {
                    // Add a barline
                    song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).setBarline(new Barline("right", "light-heavy"));
                } else if (note.getnote1()!=-1 && note.getnote1()!=-2) {

                    System.out.println("TECHNIQUE!");
                    System.out.println(note.getTech());
                    System.out.println();
                    System.out.println("GRACE!");
                    System.out.println(note.getGrace());

                    // If the note is a normal note, add the note to the song
                    if (counter != notes.size() - 1) {
                        nextNote = notes.get(counter + 1);
                        noteDashes = nextNote.getindex() - note.getindex();
                        //div = 2
                        doubleNoteDur = ((realDivisionCalc * 4.0) * ((noteDashes/(double) textReader.numberOfDashes(inputFilePath))*totalMeasureDuration));
                        noteDur = (int) doubleNoteDur;
                        if (noteDur == 0) {
                            chordNotes.add(note);
                            lastNoteChord = true;
                        } else if (noteDur != 0 && lastNoteChord) {
                            chordNotes.add(note);
                            lastNoteChord = false;
                            elementCounter = 0;
                            for (Output chordNote : chordNotes) {
                                lastPart = song.getParts().get(song.getParts().size() - 1);
                                int measureCount = lastPart.getMeasures().size();
                                if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
                                    MEASURE_POSITION_MAP.put(1, new Integer[]{chordNote.getLine(), chordNote.getLineCol()});
                                }
                                song.addNoteToMeasure(chordNote.getletter(), chordNote.getnote1());
                                lastNote = getLastNote(song);
                                lastNote.setDuration(noteDur);
                                lastNote.setType(noteType(noteDur, realDivisionCalc));
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
                                lastPart = song.getParts().get(song.getParts().size() - 1);
                                int measureCount = lastPart.getMeasures().size();
                                if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
                                    MEASURE_POSITION_MAP.put(1, new Integer[]{note.getLine(), note.getLineCol()});
                                }
                                song.addNoteToMeasure(note.getletter(), note.getnote1());
                                lastNote = getLastNote(song);
                                lastNote.setDuration(noteDur);
                                lastNote.setType(noteType(noteDur, totalSpaces));
                            }

                        }
                    }

                    // GRACE NOTES / HAMMER-ONS
                    if ((note.getGrace().equals("g") || note.getGrace().equals("G")) && (note.getTech().equals("H") || note.getTech().equals("h"))) {

                        getLastNote(song).setGrace(new Grace());
                        getLastNote(song).getGrace().setSlash("yes");
                        ArrayList<HammerOn> hammerOns = new ArrayList<HammerOn>();
                        hammerOns.add(new HammerOn(1, "start", "H"));
                        getLastNote(song).getNotations().getTechnical().setHammer(hammerOns);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setType("start");
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setSymbol("H");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "start"));
                    }

                    // GRACE NOTES / PULL-OFFS
                    if ((note.getGrace().equals("g") || note.getGrace().equals("G")) && (note.getTech().equals("P") || note.getTech().equals("p"))) {
                        getLastNote(song).setGrace(new Grace());
                        getLastNote(song).getGrace().setSlash("yes");
                        ArrayList<PullOff> pullOffs = new ArrayList<>();
                        pullOffs.add(new PullOff(1, "start", "P"));
                        getLastNote(song).getNotations().getTechnical().setPulloff(pullOffs);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setType("start");
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setSymbol("P");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "start"));
                    }

                    // HAMMER-ONS
                    if (note.getTech().equals("H") || note.getTech().equals("h")) {

                        // note 1
                        ArrayList<HammerOn> hammerOns = new ArrayList<HammerOn>();
                        hammerOns.add(new HammerOn(1, "start", "H"));
                        getLastNote(song).getNotations().getTechnical().setHammer(hammerOns);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setType("start");
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setSymbol("H");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "start"));

                        // note 2
                        ArrayList<HammerOn> hammerOns2 = new ArrayList<HammerOn>();
                        hammerOns2.add(new HammerOn());
                        song.addNoteToMeasure(note.getletter(), note.getnote2());
                        getLastNote(song).setType("half");
                        getLastNote(song).getNotations().getTechnical().setHammer(hammerOns2);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setType("stop");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "stop"));

                    }

                    // PULL-OFFS
                    if (note.getTech().equals("P") || note.getTech().equals("p")) {

                        // first note of technique
                        ArrayList<PullOff> pullOffs = new ArrayList<>();
                        pullOffs.add(new PullOff(1, "start", "P"));
                        getLastNote(song).getNotations().getTechnical().setPulloff(pullOffs);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setType("start");
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setSymbol("P");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "start"));

                        // second note of technique
                        ArrayList<PullOff> pullOffs2 = new ArrayList<PullOff>();
                        pullOffs2.add(new PullOff());
                        song.addNoteToMeasure(note.getletter(), note.getnote2());
                        getLastNote(song).setType("half");
                        getLastNote(song).getNotations().getTechnical().setPulloff(pullOffs2);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setType("stop");
                        getLastNote(song).getNotations().setSlur(new Slur("1", "stop"));
                    }

                    // SLIDES
                    if (note.getTech().equals("S") || note.getTech().equals("s")) {

                        // first note of technique
                        ArrayList<Slide> slide = new ArrayList<>();
                        slide.add(new Slide(1, "start"));
                        getLastNote(song).getNotations().setSlide(slide);
                        getLastNote(song).getNotations().getSlide().get(0).setNumber(1);
                        getLastNote(song).getNotations().getSlide().get(0).setType("start");

                        // second note of technique
                        ArrayList<Slide> slide2 = new ArrayList<>();
                        slide2.add(new Slide());
                        song.addNoteToMeasure(note.getletter(), note.getnote2());
                        getLastNote(song).setType("half");
                        getLastNote(song).getNotations().setSlide(slide2);
                        getLastNote(song).getNotations().getSlide().get(0).setNumber(1);
                        getLastNote(song).getNotations().getSlide().get(0).setType("stop");
                    }

                    // HARMONICS NOTE DONE
                    if (note.getTech().equals("[]")) {
                        getLastNote(song).getNotations().getTechnical().setHarmonic(new Harmonic(""));
                    }

                }

                prevNote = note;
                counter++;
            }
        }
        //}

        // Instrument == 2: Parse as Bass XMLTags.Guitar
        else if (instrument == 2) {
            System.out.println("bass guitar");
            attribs.setDivisions(Integer.toString(1));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
            counter = 0;
            for (Output note : notes) {
                if ((/*note.getnote1() == -2 || */note.getnote1() == -1) && counter != notes.size() - 1) { //TEST THIS: before, the getnote1 == -2 condition was commented
                    lastPart = song.getParts().get(song.getParts().size() - 1);

                    int measureNum = lastPart.getMeasures().size() + 1;
                    lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), measureNum));
                    MEASURE_POSITION_MAP.put(measureNum, new Integer[]{note.getLine(), note.getLineCol()});
					/*
					lastPart.getMeasures().get(lastPart.getMeasures().size() - 1).getAttributes().setDivisions("2");*/
                    prevNote = new Output();
                } else {
                    if (counter != notes.size() - 1 && (note.getnote1() != -1 && note.getnote1() != -2)) {
                        song.addNoteToMeasure(note.getletter(), note.getnote1());
                        getLastNote(song).getPitch().setOctave(song.getLastPart().getLastMeasure().getLastNote().getPitch().getOctave() - 1);
                        getLastNote(song).setType("half");
                    }
                    if (prevNote.getindex() == note.getindex())
                        getLastNote(song).chordOn();
                }
                prevNote = note;
                counter++;
            }
        } else if (instrument == 3) {
            System.out.println("*********************************drum tablature detected");
            attribs.setDivisions(Integer.toString(1));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
            counter = 0;
        } else if (instrument == 3) {
            attribs.setDivisions(Integer.toString(1));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
            counter = 0;
        } else if (instrument == 3) {
            attribs.setDivisions(Integer.toString(1));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
            counter = 0;
            song.getPartList().getScorePart().setPartName("Drum 1");

            for (Output note : notes) {
                System.out.println(note.getletter() + " " + note.getTech());
            }
        }

        serialize(song, xmlFile);
        deSerialize(xmlFile);
    }

//	public static void addTech(ConvertedSong song, Output note, ){
//		if(note.getTech().equals("H") || note.getTech().equals("h")){
//
//		}
//
//		if(note.getTech().equals("P") || note.getTech().equals("p")){
//
//		}
//	}
}