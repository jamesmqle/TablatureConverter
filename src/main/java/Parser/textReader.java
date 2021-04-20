package Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static Parser.BassParser.ParseBass;
import static Parser.DrumParser.ParseDrum;
import static Parser.GuitarParser.ParseGuitar;

/**
 * TODO: 	1. Calculate how many dashes from first note to end of measure
 * 2. Calculate the shortest note duration (but return 1 if any note duration is odd)
 */

public class textReader extends Output {

    public textReader(String string, int n1, int n2, String tech, int i) {
        super(string, n1, n2, tech, i);
        // TODO Auto-generated constructor stub
    }

    /**
     * getTab:
     * This method returns the tablature at the specified file path as a list of strings (one tab line per element)
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static List<String> getTab(String path) throws FileNotFoundException {
        List<String> tab = new ArrayList<>();

        Scanner myReader = new Scanner(new FileReader(path));
        String data = null;

        //If there are more lines in the tablature, add them to the list of strings  (tab)
        while (myReader.hasNextLine()) {
            data = myReader.nextLine().trim();
            tab.add(data);
        }

        return tab;
    }

    /**
     * "detectInstrument" returns the instrument that is being described by the provided tablature file
     * at a given filepath.
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static int detectInstrument(String path) throws FileNotFoundException {

        int instrument = 0;
        int k=0;
        int flag1 = 0, flag2 = 0;

        Scanner myReader = new Scanner(new FileReader(path));
        String data;

        while (myReader.hasNextLine()) {
            data = myReader.nextLine().trim();

            for(int i=0;i<data.length();i++) {
                if ((data.charAt(i) == 'x' || data.charAt(i) == 'X' || data.charAt(i) == 'o' || data.charAt(i) == 'O')) {
                    flag1 = 1;
                }
                if (data.charAt(i) == '|' || data.charAt(i) == '-') {
                    flag2 = 1;
                }
                if (flag1 * flag2 == 1) {
                    // it is drum
                    return 3;
                }

            }

            if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
                //tab.add(data);
                if(k==6||k==7){
                    //it is guitar
                    return 1;
                } else if(k==4||k==5){
                    //it is bass
                    return 2;
                }
            }
            else {
                k++;
            }

        }

        if(k==6||k==7){
            //it is guitar
            return 1;
        } else if(k==4||k==5){
            //it is bass
            return 2;

        }
        // no instrument detected
        return 0;
    }

    public static int detectNumberStrings(String path) throws FileNotFoundException {
        int k=0;
        Scanner myReader = new Scanner(new FileReader(path));
        String data;

        while (myReader.hasNextLine()) {
            data = myReader.nextLine().trim();

            if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
                return k;
            }
            else {
                k++;
            }

        }
        return k;
    }

    /**
     * "readTabFile" Takes the path of the file and reads it to recognize the
     * instrument and call the proper instrument parser function. returns the list
     * of output which is the result
     */
    public static List<Output> readTabFile(String path) throws FileNotFoundException {
        // define the result list
        // and list of string which is a single tab
        List<Output> list = new ArrayList<>();
        LinkedHashMap<Integer, String> tab = new LinkedHashMap();
        int instrument = 0, k = 0;
        int numStrings = 0;

        // scan the txt file
        Scanner myReader = new Scanner(new FileReader(path));
        String data;

        // while loop which is for each line of the file and stops if there is no line
        // anymore

        instrument = detectInstrument(path);
        numStrings = detectNumberStrings(path);

        int line = 0;
        while (myReader.hasNextLine()) {
            line++;
            data = myReader.nextLine();

            /*
             * If instrument is guitar it adds lines of tab to the tab list until if ends
             * which is after 6 strings or lines
             */
            if (instrument == 1) { // XMLTags.Guitar
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.put(line, data);
                    k++;
                }

                if (k == numStrings) {
                        //TabIsOK(Arrays.asList((String[])tab.keySet().toArray()), instrument);
                        list = ParseGuitar(tab, list,numStrings);
                        k = 0;
                        tab.clear();
                }

                /*
                 * If instrument is bass it adds lines of tab to the tab list until if ends
                 * which is after 4 strings or lines
                 */
            } else if (instrument == 2) { // Bass
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.put(line, data);
                    k++;
                }

                if (k == numStrings) {
                        //TabIsOK(tab, instrument);
                        list = ParseBass(tab, list,numStrings);
                        k = 0;
                        tab.clear();
                }
            }

            else if (instrument == 3) {// Drum
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.put(line, data);
                    k++;
                }

                if (k == numStrings) {
                    // first handle exceptions and check if it is correct tab, then call the
                    // parsDrum function

                }

            }
        }

        // close the file and return the result
        myReader.close();
        //ConvertedSongTest.createXML(list);
        return list;
    }

    /**
     * TabIsOk checks that the inputted tablature is a valid format for the given instrument tablature
     * 0 - No error
     * 1 - Uneven line length
     * 2 - Incorrect tuning
     * 10 - No instrument detected
     */
    public static int TabIsOK(List<String> tab, int instrument) {
        int warningError = 0;
        System.out.println("Instrument: " + instrument);

        if (instrument == 1) {// Guitar
            // check all lines have the same length
            System.out.println("tab size: " + tab.size());
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    System.out.println("tab 1: " + tab.get(i).length());
                    System.out.println("tab 2: " + tab.get(i).length());
                    return  1; // error 1 if all lines are not the same length
                }
            }

            for(int j=0;j<tab.size();j++) {
                // check all lines have the correct tuning letter
                if (!((getCharFromString(tab.get(j), 0) >= 65 && getCharFromString(tab.get(0), 0) <= 71) || (getCharFromString(tab.get(j), 0) >= 97 && getCharFromString(tab.get(j), 0) <= 103))) {
                    return 2; // error 2 if incorrect tuning letter
                }
            }

        } else if (instrument == 2) {// Bass
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    return 1; // error 1 if all ines are not the same length
                }

                for(int j=0;j<tab.size();j++) {
                    // check all lines have the correct tuning letter
                    if (!((getCharFromString(tab.get(j), 0) >= 65 && getCharFromString(tab.get(0), 0) <= 71) || (getCharFromString(tab.get(j), 0) >= 97 && getCharFromString(tab.get(j), 0) <= 103))) {
                        System.out.println(getCharFromString(tab.get(j),0));
                        return 2; // error 2 if incorrect tuning letter
                    }
                }
            }

        } else if (instrument == 3) {// Drum
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    return 1; // error 1 if all lines are not the same length
                }
            }
        } else if(instrument == 4) { // no instrument detected
            return 10; // critical error
        }

        System.out.println("Text Reader Error: " + warningError);
        return warningError;
    }

    /**
     * element 0 - is the error
     * element 1 - is the index
     * element 2 - is the line
     * @param tab
     * @param instrument
     * @return
     */
    public static int[] TabIsOKTracker(List<String> tab, int instrument) {
        int[] arr = new int[3];

        System.out.println("Instrument: " + instrument);

        if (instrument == 1) {// Guitar

            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    arr[0] = 1;
                    arr[1] = -1;
                    arr[2] = i;
                }
            }

            // check all lines have the correct tuning letter
            for(int j=0;j<tab.size();j++) {
                if (!((getCharFromString(tab.get(j), 0) >= 65 && getCharFromString(tab.get(j), 0) <= 71) || (getCharFromString(tab.get(j), 0) >= 97 && getCharFromString(tab.get(j), 0) <= 103))) {
                    arr[0] = 2;
                    arr[1] = 0;
                    arr[2] = j;
                }
            }

        } else if (instrument == 2) {// Bass
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    arr[0] = 1;
                    arr[1] = -1;
                    arr[2] = i;
                }

                for(int j=0;j<tab.size();j++) {
                    // check all lines have the correct tuning letter
                    if (!((getCharFromString(tab.get(j), 0) >= 65 && getCharFromString(tab.get(0), 0) <= 71) || (getCharFromString(tab.get(j), 0) >= 97 && getCharFromString(tab.get(j), 0) <= 103))) {
                        System.out.println(getCharFromString(tab.get(j),0));
                        arr[0] = 2;
                        arr[1] = 0;
                        arr[2] = j;
                    }
                }
            }

        } else if (instrument == 3) {// Drum
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    arr[0] = 1;
                    arr[1] = -1;
                    arr[2] = i;
                }
            }
        } else { // no instrument detected
            arr[0] = 10;
            arr[1] = -1;
            arr[2] = -1;
        }

        return arr;
    }

    /**
     * "isInteger" Takes the string and return the boolean true if the string is an
     * integer false if the string is not an integer
     */
    public static boolean isInteger(String s) {
        try{
            Integer.parseInt(s);
            return true;

        }catch(NumberFormatException e){
            return false;
        }
    }

    /*
     * "getCharFromString" Takes a line of string and an index. returns the
     * character in the line at the index
     */
    public static char getCharFromString(String str, int index) {
        return str.charAt(index);
    }

    // Print data of the output list that we return
    public static void printData(List<Output> list) {
        list.forEach(obj -> System.out.println("Tunning :" + obj.getletter() + "\t" + "note 1 :" + obj.getnote1() + "\t"
                + "note 2 :" + obj.getnote2() + "\t" + "Technique :" + obj.getTech() + "\t" + "i :" + obj.getindex()));
    }

    /**
     *  1. Calculate how many dashes from first note to end of measure
     *	2. Calculate the shortest note duration (but return 1 if any note duration is odd)
     **/

    public static int numberOfDashes(String filepath) throws FileNotFoundException {
        List<Output> list = new ArrayList<>();
        list = readTabFile(filepath);
        /*        printData(list);*/
        int firstIndex = -1, measureIndex = -1, numDash = -1, oldNumDash = -1;
        int counter = 0, isFirst = 0;

        for (Output note : list){
            if (counter == 0 && (note.getnote1() != -1 && note.getnote1() != -2) ) {
                firstIndex = note.getindex();
                counter++;
            }
            if (note.getnote1() == -1) {
                counter = 0;
                measureIndex = note.getindex();
                if (isFirst == 0) {
                    oldNumDash = measureIndex - firstIndex;
                    isFirst++;
                } else {
                    numDash = measureIndex - firstIndex;
                }
            }
        }

        return oldNumDash;
    }

    public static int numberOfDashesDrum(String filepath) throws FileNotFoundException {
        List<Output> list = new ArrayList<>();
        list = readTabFile(filepath);
        /*        printData(list);*/
        int firstIndex = -1, measureIndex = -1, numDash = -1, oldNumDash = -1, finalDash = -1;
        int counter = 0, isFirst = 0;

        for (Output note : list){
            // if not new line or new measure, it is the first note in the measure
            if (counter == 0 && (note.getnote1() != -1 && note.getnote1() != -2) ) {
                firstIndex = note.getindex();
                counter++;
            }
            if (note.getnote1() == -1) {
                counter = 0;
                measureIndex = note.getindex();
                // i dont know what this block is but i'm too afraid to remove it
                if (isFirst == 0) {
                    oldNumDash = measureIndex - firstIndex;
                    isFirst++;
                    finalDash = oldNumDash;
                } else {
                    numDash = measureIndex - firstIndex;
                    System.out.println("oldNumDash: " + oldNumDash);
                    System.out.println("numDash: " + numDash);
                    if (oldNumDash < numDash) finalDash = numDash;
                }
            }
        }

        return finalDash;
    }

    /**
     * Calculate the shortest note duration (but return 1 if any note duration is odd)
     * @param filepath
     * @return
     * @throws FileNotFoundException
     */

    public static int shortestNoteDuration(String filepath) throws FileNotFoundException{
        /**
         * 1. Declare first note
         * 2. Subtract note.index - prevNote.index
         * 3. Compare the difference to min note dur
         * 4. Check for odd
         */
        List<Output> list = new ArrayList<>();
        list = readTabFile(filepath);
        int shortestDuration; // compare there 2 indexes to find the difference
        int noteDur = 1002, minNoteDur = 1000; // Duration and shortest duration between notes
        Output prevNote = new Output();

        int counter = 0;
        for(Output note : list){
            if(counter == 0) counter++;
            else{
                noteDur = note.getindex() - prevNote.getindex();
                //System.out.println(noteDur);
            }

            if(minNoteDur > noteDur && noteDur != 0){
                minNoteDur = noteDur;
            }

            prevNote = note;
        }

        return minNoteDur;
    }

}