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
        double noteVal = (4.0 * (double) divisions)/noteDuration;
        if (noteVal>=1024)
            return "1024th";
        else if (noteVal>=512)
            return "512th";
        else if (noteVal>=256)
            return "256th";
        else if (noteVal>=128)
            return "128th";
        else if (noteVal>=64)
            return "64th";
        else if (noteVal>=32)
            return "32nd";
        else if (noteVal>=16)
            return "16th";
        else if (noteVal>=8)
            return "eighth";
        else if (noteVal>=4)
            return "quarter";
        else if (noteVal>=2)
            return "half";
        else if (noteVal>=1)
            return "whole";
        else if (noteVal>=0.5)
            return "breve";
        else if (noteVal>=0.25)
            return "long";
        else if (noteVal>=0.125)
            return "maxima";
        return "";
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

    public static void createXML(String outputFilePath, String inputFilePath) throws FileNotFoundException {

        int instrument = textReader.detectInstrument(inputFilePath);
        List<Output> notes = textReader.readTabFile(inputFilePath);
        notes = textReader.applyDurationRatios(notes);

        String xmlFile = outputFilePath;
        ConvertedSong song = new ConvertedSong();
        Part lastPart;
        boolean isPerfect, lastNoteChord = false;
        double divisionCalc, doubleNoteDur;
        int realDivisionCalc, counter = 0, noteDur, noteDashes, totalSpaces = -1, elementCounter = 0, numDD = 0;
        Output nextNote;
        Note lastNote;

        if (notes.get(0).getnote1()!=-1) {
            notes.add(0, new Output("*New Measure*", -1, -1, "-", notes.get(0).getindex()-1));
        }

        MEASURE_POSITION_MAP.clear();

        List<Output> chordNotes = new ArrayList<Output>(); // create list for all notes on same line (chord)


        // Initalize part (and first measure)
        song.addPart(new Part());
        //song.getLastPart().getMeasures().get(0).getAttributes().setStaffDetails(new StaffDetails(instrument));

        // declare staff details
        //song.getLastPart().getMeasures().get(0).getAttributes().setStaffDetails(new StaffDetails(instrument));

        // Get attributes from the first measure (for subsequent measures)
        // doesnt do anything?
        if (Controller.songTitle != null)
            song.setWork(new Work(Controller.songTitle));

        Matcher matcher = Pattern.compile("[0-9]+").matcher(Controller.timeSignature);
        matcher.find();
        double beatCount = Integer.parseInt(matcher.group());
        matcher.find();
        double beatType = Integer.parseInt(matcher.group());

        double totalMeasureDuration = beatCount*(1.0/beatType);
        double shortestDuration = textReader.shortestNoteDurationRatio(inputFilePath)*totalMeasureDuration;
        double roundDuration = 1/Math.pow(2, Math.ceil(Math.log(1/shortestDuration)/Math.log(2)));
        divisionCalc = (1.0 / (roundDuration)) / 4.0;

        realDivisionCalc = (int)Math.ceil(divisionCalc);


        // Instrument == 1: Parse as XMLTags.Guitar OR Instrument == 2: Parse as XML.Bass
        if (instrument == 1 || instrument == 2) {
            /**
             * note1 corresponds to:
             * -2 -> new tab line
             * -1 -> new measure
             * else -> note
             */

            counter = 0;
            song.getPartList().getScorePart().setPartName("Guitar 1");

            for (Output note : notes) {
                // Add measure
                if ((note.getnote1() == -1) && counter != notes.size() - 1) {
                    // Get Part - Guitar 1
                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    //TODO Note: we left out the if statement here that was in the original thing
                    int measureNum = lastPart.getMeasures().size() + 1;
                    Measure measure = new Measure(new Attributes(), new ArrayList<Note>(), measureNum);
                    lastPart.addMeasure(measure);
                    if (measureNum==1)
                        setFirstMeasureAttributes(measure, realDivisionCalc, (int)beatCount, (int)beatType, instrument);
                    MEASURE_POSITION_MAP.put(measureNum, new Integer[]{note.getLine(), note.getLineCol()});
                }

                // Add a barline
                else if (note.getnote1() == -1 && counter == notes.size() - 1) {
                    song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).setBarline(new Barline("right", "light-heavy"));
                }

                // Normal Note
                else if (note.getnote1()!=-1 && note.getnote1()!=-2) {

                    double tmpDuration = note.getDurationRatio()*totalMeasureDuration;
                    double a = Math.log(1 / tmpDuration) / Math.log(2);
                    double roundUpDuration = 1/Math.pow(2, Math.ceil(a));
                    double roundDownDuration = 1/Math.pow(2, Math.floor(a));
                    double roundedDuration;
                    if (tmpDuration<(roundUpDuration+roundDownDuration)/2)
                        roundedDuration = roundDownDuration;
                    else
                        roundedDuration = roundUpDuration;
                    doubleNoteDur = ((realDivisionCalc * 4.0) * (roundedDuration));
                    noteDur = (int) Math.max(1, doubleNoteDur);

                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    int measureCount = lastPart.getMeasures().size();
                    if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
                        MEASURE_POSITION_MAP.put(1, new Integer[]{note.getLine(), note.getLineCol()});
                    }
                    song.addNoteToMeasure(note.getletter(), note.getnote1());
                    lastNote = getLastNote(song);
                    lastNote.setDuration(noteDur);
                    lastNote.setType(noteType(noteDur, realDivisionCalc));
                    if (instrument==2)
                        lastNote.getPitch().setOctave(song.getLastPart().getLastMeasure().getLastNote().getPitch().getOctave()-1);

                    if (note.isChord()) {
                        lastNote.chordOn();
                    }

                    Output nextNoteTech = null;
                    if (counter+1<notes.size()) {
                        nextNoteTech = notes.get(counter+1);
                    }


                    // GRACE NOTES / HAMMER-ONS
                    if (note.getGrace().equalsIgnoreCase("g") && nextNoteTech!=null && (nextNoteTech.getTech().equalsIgnoreCase("h")||nextNoteTech.getTech().equalsIgnoreCase("p"))) {
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
                        getLastNote(song).getNotations().getTechnical().setHammer(hammerOns2);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setNumber(1);
                        getLastNote(song).getNotations().getTechnical().getHammer().get(0).setType("stop");
                        getLastNote(song).getNotations().setSlur(new Slur(1, "stop"));
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

                    // PULL-OFFS
                    else if (note.getTech().equals("P") || note.getTech().equals("p")) {

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
                        getLastNote(song).getNotations().setSlide(slide2);
                        getLastNote(song).getNotations().getSlide().get(0).setNumber(1);
                        getLastNote(song).getNotations().getSlide().get(0).setType("stop");
                    }

                    // HARMONICS NOTE DONE
                    if (note.getTech().equals("[]")) {
                        getLastNote(song).getNotations().getTechnical().setHarmonic(new Harmonic(""));
                    }

                }
                counter++;
            }
        }

        // Drum Implementation
        else if (instrument == 3){
            initializeDrumScoreInstruments(song);/*
			attribs.setDivisions(Integer.toString(4));*/
//            System.out.println("shortest note: " + (double) textReader.shortestNoteDuration(inputFilePath));
//            divisionCalc = textReader.numberOfDashesDrum(inputFilePath)/(double)attribs.getTime().getBeats();
//            System.out.println("TOTAL numdashes: " + textReader.numberOfDashesDrum(inputFilePath));
//            realDivisionCalc = (int) divisionCalc;
//            attribs.setDivisions(Integer.toString(realDivisionCalc));
            counter = 0;
            song.getPartList().getScorePart().setPartName("Drum 1");


            for (Output note : notes) {
                // Add measure
                if ((note.getnote1() == -1) && counter != notes.size() - 1) {
                    // Get Part - Guitar 1
                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    //TODO Note: we left out the if statement here that was in the original thing
                    int measureNum = lastPart.getMeasures().size() + 1;
                    Measure measure = new Measure(new Attributes(), new ArrayList<Note>(), measureNum);
                    lastPart.addMeasure(measure);
                    if (measureNum==1)
                        setFirstMeasureAttributes(measure, realDivisionCalc, (int)beatCount, (int)beatType, instrument);
                    MEASURE_POSITION_MAP.put(measureNum, new Integer[]{note.getLine(), note.getLineCol()});
                }

                // Add a barline
                else if (note.getnote1() == -1 && counter == notes.size() - 1) {
                    song.getParts().get(song.getParts().size() - 1).getMeasures().get(song.getParts().get(song.getParts().size() - 1).getMeasures().size() - 1).setBarline(new Barline("right", "light-heavy"));
                }

                // Normal Note
                else if (note.getnote1()!=-1 && note.getnote1()!=-2) {

                    double tmpDuration = note.getDurationRatio() * totalMeasureDuration;
                    double a = Math.log(1 / tmpDuration) / Math.log(2);
                    double roundUpDuration = 1 / Math.pow(2, Math.ceil(a));
                    double roundDownDuration = 1 / Math.pow(2, Math.floor(a));
                    double roundedDuration;
                    if (tmpDuration < (roundUpDuration + roundDownDuration) / 2)
                        roundedDuration = roundDownDuration;
                    else
                        roundedDuration = roundUpDuration;
                    doubleNoteDur = ((realDivisionCalc * 4.0) * (roundedDuration));
                    noteDur = (int) Math.max(1, doubleNoteDur);

                    lastPart = song.getParts().get(song.getParts().size() - 1);
                    int measureCount = lastPart.getMeasures().size();
                    if (measureCount == 1 && !MEASURE_POSITION_MAP.containsKey(1)) {
                        MEASURE_POSITION_MAP.put(1, new Integer[]{note.getLine(), note.getLineCol()});
                    }
                    song.addDrumNoteToMeasure(note.getletter(), note.getTech());
                    lastNote = getLastNote(song);
                    lastNote.setDuration(noteDur);
                    lastNote.setType(noteType(noteDur, realDivisionCalc));
                    if (instrument == 2)
                        lastNote.getPitch().setOctave(song.getLastPart().getLastMeasure().getLastNote().getPitch().getOctave() - 1);

                    if (note.isChord()) {
                        lastNote.chordOn();
                    }
                }
                counter++;
            }
        }

        serialize(song, xmlFile);
        deSerialize(xmlFile);
    }

    public static void setFirstMeasureAttributes(Measure measure, int divisions, int beatCount, int beatType, int instrument) {
        measure.setAttributes(new Attributes());
        measure.getAttributes().setStaffDetails(new StaffDetails(instrument));
        measure.getAttributes().setDivisions(String.valueOf(divisions));
        measure.getAttributes().setClef(instrument==3 ? new Clef("percussion", 2) : new Clef());
        measure.getAttributes().setTime(new TimeSignature(beatCount, beatType));
        measure.getAttributes().setKey(new Key(0, "major"));

    }
}