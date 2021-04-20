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
import XMLTags.Drums.ScoreInstrument;
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
        Part lastPart = song.getParts().get(song.getParts().size() - 1);
        Measure lastMeasure = lastPart.getMeasures().get(lastPart.getMeasures().size() - 1);
        Note lastNote = lastMeasure.getNotes().get(lastMeasure.getNotes().size() - 1);
        return lastNote;
    }
    private static Note getSecondToLastNote(ConvertedSong song) {
        Part lastPart = song.getParts().get(song.getParts().size() - 1);
        Measure lastMeasure = lastPart.getMeasures().get(lastPart.getMeasures().size() - 1);
        if (lastMeasure.getNotes().size() - 2<0)
            return null;
        else
            return lastMeasure.getNotes().get(lastMeasure.getNotes().size() - 2);
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

    public static String noteType2(int noteDashes, int totalDashes) {
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
        System.out.println("Controller songtitle:" + Controller.songTitle);
        song.setWork(new Work(Controller.songTitle));

        // Instrument == 1: Parse as XMLTags.Guitar
        if (instrument == 1) {
            /**
             * note1 corresponds to:
             * -2 -> new tab line
             * -1 -> new measure
             * else -> note
             */

            counter = 0;

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
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
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

                    // If the note is a normal note, add the note to the song
                    //if (counter != notes.size() - 1) {
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
                        if (note.getnote1() == -2);
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
                        getLastNote(song).getNotations().setSlur(new Slur(1, "start"));
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
                        getLastNote(song).getNotations().setSlur(new Slur(1, "start"));
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
                        getLastNote(song).getNotations().setSlur(new Slur(1, "start"));

                        // note 2
                        ArrayList<HammerOn> hammerOns2 = new ArrayList<HammerOn>();
                        hammerOns2.add(new HammerOn());
                        song.addNoteToMeasure(note.getletter(), note.getnote2());
                        getLastNote(song).setType("half");
                        getLastNote(song).getNotations().getTechnical().setHammer(hammerOns2);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setType("stop");
                        getLastNote(song).getNotations().setSlur(new Slur(1, "stop"));

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
                        getLastNote(song).getNotations().setSlur(new Slur(1, "start"));

                        // second note of technique
                        ArrayList<PullOff> pullOffs2 = new ArrayList<PullOff>();
                        pullOffs2.add(new PullOff());
                        song.addNoteToMeasure(note.getletter(), note.getnote2());
                        getLastNote(song).setType("half");
                        getLastNote(song).getNotations().getTechnical().setPulloff(pullOffs2);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getPulloff().get(0).setType("stop");
                        getLastNote(song).getNotations().setSlur(new Slur(1, "stop"));
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
        }

        // Drum Implementation
        else if (instrument == 3){
            initializeDrumScoreInstruments(song);/*
			attribs.setDivisions(Integer.toString(4));*/
            attribs.setClef(new Clef("percussion", 2));
            System.out.println("shortest note: " + (double) textReader.shortestNoteDuration(inputFilePath));
            divisionCalc = textReader.numberOfDashesDrum(inputFilePath)/(double)attribs.getTime().getBeats();
            System.out.println("TOTAL numdashes: " + textReader.numberOfDashesDrum(inputFilePath));
            realDivisionCalc = (int) divisionCalc;
            attribs.setDivisions(Integer.toString(realDivisionCalc));
            song.getParts().get(0).getMeasures().get(0).setAttributes(attribs);
            counter = 0;
            song.getPartList().getScorePart().setPartName("Drum 1");

            for (Output note: notes){
                System.out.println("Marking: " + note.getTech() + "   Note 1: " + note.getnote1() + "   Letter: " + note.getletter());
            }

            for (Output note: notes){
                if ((note.getnote1() == -1) && counter != notes.size() - 1) {
                    // Get last part in the song - Drum 1
                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    // Add measure to the part
                    lastPart.addMeasure(new Measure(new Attributes(), new ArrayList<Note>(), lastPart.getMeasures().size() + 1));
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

                        double noteType = 0;
                        int numDashToNext = 0, noteTypeInt = 0;

                        for (Output drumNote : notes){
                            if (drumNote.getindex() > note.getindex()){
                                System.out.println("Note index: " + drumNote.getindex() + " Getnote1: " + drumNote.getnote1() + " Getletter: " + drumNote.getletter());
                                numDashToNext = drumNote.getindex() - note.getindex();
                                break;
								/*if (drumNote.getletter().equals(note.getletter()) || drumNote.getnote1() == -1) {
									numDashToNext = drumNote.getindex() - note.getindex();
									break;
								}*/
                            }
                        }
                        if (numDashToNext == 0) numDashToNext = 1;

                        noteType = (double)textReader.numberOfDashesDrum(inputFilePath)/(double)numDashToNext;
                        noteTypeInt = (int) noteType;

                        int pow2 = 128, numDashToNextTemp = numDashToNext;
                        boolean isFirst = true;
                        Output tempNote = new Output();
                        System.out.println(counter);
                        if (isPowerOfTwo(numDashToNextTemp)){
                            song.addDrumNoteToMeasure(note.getletter(), note.getTech(), numDashToNext);
                            getLastNote(song).setType(noteType2(numDashToNext, textReader.numberOfDashesDrum(inputFilePath)));
                        }
                        else{
                            while (numDashToNextTemp > 0){
                                //first check for the biggest power of two that is less than the number of dashes
                                // on the first less than number of dashes, add the note with that duration power of two
                                //System.out.println("entered loop");
                                if (pow2 <= numDashToNextTemp){
                                    song.addDrumNoteToMeasure(note.getletter(), note.getTech(), pow2);
                                    //set note type
                                    getLastNote(song).setType(noteType2(getLastNote(song).getDuration(), textReader.numberOfDashesDrum(inputFilePath)));

                                    isFirst = false;
                                    numDashToNextTemp = numDashToNextTemp - pow2;
                                    if (numDashToNextTemp != 0){
                                        //add tie to note
                                        getLastNote(song).setNotations(new Notation(new Slur(1, "start"), new Tied("start")));
                                        getLastNote(song).setTie(new Tie("start"));
                                    }
                                    else if (numDashToNextTemp == 0){
                                        getLastNote(song).setNotations(new Notation(new Slur(1, "stop"), new Tied("stop")));
                                        getLastNote(song).setTie(new Tie("stop"));
                                    }
                                    tempNote = note;
                                    //System.out.println(numDashToNextTemp);
                                }
                                else {
                                    pow2 = pow2/2;
                                }
                            }
                        }

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