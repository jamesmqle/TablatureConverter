package Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

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

        Scanner myReader = new Scanner(new FileReader(path));
        String data;

        while (myReader.hasNextLine()) {
            data = myReader.nextLine().trim();

            for(int i=0;i<data.length();i++){
                if((data.charAt(i) == 'x')||(data.charAt(i) == 'X')){
                    // it is drum
                    return 3;
                }
            }

            if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
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

        return 4;
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
        List<String> tab = new ArrayList<>();
        int instrument = 0, k = 0;

        // scan the txt file
        Scanner myReader = new Scanner(new FileReader(path));
        String data;

        // while loop which is for each line of the file and stops if there is no line
        // anymore

        instrument = detectInstrument(path);

        while (myReader.hasNextLine()) {

            data = myReader.nextLine().trim();

            /*
             * If instrument is guitar it adds lines of tab to the tab list until if ends
             * which is after 6 strings or lines
             */
            if (instrument == 1) { // XMLTags.Guitar
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.add(data);
                    k++;
                }

                if (k == 6) {
                    try {
                        TabIsOK(tab, instrument);
                        list = ParseGuitar(tab, list);
                        k = 0;
                        tab.clear();
                    } catch (StringIndexOutOfBoundsException e) {
                        // handleException();
                        System.out.println(
                                "Exception occurred . . . . Strings do not have the same length or do have wrong tunning!!");
                    }
                }

            /*
             * If instrument is bass it adds lines of tab to the tab list until if ends
             * which is after 4 strings or lines
             */
            } else if (instrument == 2) { // Bass
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.add(data);
                    k++;
                }

                if (k == 4) {
                    try {
                        TabIsOK(tab, instrument);
                        list = ParseBass(tab, list);
                        k = 0;
                        tab.clear();
                    } catch (StringIndexOutOfBoundsException e) {
                        // handleException();
                        System.out.println(
                                "Exception occurred . . . . Strings do not have the same length or they do have wrong tunning!!");
                    }
                }
            }

            else if (instrument == 3) {// Drum
                if (!(data.isEmpty()) || (data.charAt(0) == ' ')) {
                    tab.add(data);
                    k++;
                }

                if (data.charAt(0) == 'B') {
                    // first handle exceptions and check if it is correct tab, then call the
                    // parsDrum function
                    try {
                        TabIsOK(tab, instrument);
                        list = ParseDrum(tab, list, k);
                        k = 0;
                        tab.clear();

                    } catch (StringIndexOutOfBoundsException e) {
                        // handleException();
                        System.out.println(
                                "Exception occurred . . . . Strings do not have the same length or They do have wrong tunning!!");
                    }
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
     */
    public static int TabIsOK(List<String> tab, int instrument) {
        int warningError = 0;
        System.out.println("Flag: " + instrument);

        if (instrument == 1) {// Guitar
            // check all lines have the same length
            System.out.println("tab size: " + tab.size());
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    System.out.println("tab 1: " + tab.get(i).length());
                    System.out.println("tab 2: " + tab.get(i).length());
                    warningError = 1; // error 1 if all lines are not the same length
                }
            }

            // check all lines have the correct tuning letter
            if ((getCharFromString(tab.get(0), 0) != 'e') || (getCharFromString(tab.get(1), 0) != 'B') || (getCharFromString(tab.get(2), 0) != 'G')
                    || (getCharFromString(tab.get(3), 0) != 'D') || (getCharFromString(tab.get(4), 0) != 'A')
                    || (getCharFromString(tab.get(5), 0) != 'E')) {
                warningError = 2; // error 2 if incorrect tuning letter
            }

        } else if (instrument == 2) {// Bass
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    warningError = 1; // error 1 if all ines are not the same length
                }
            }
            // check all lines have the correct tuning letter
            if ((getCharFromString(tab.get(0), 0) != 'G') || (getCharFromString(tab.get(1), 0) != 'A')
                    || (getCharFromString(tab.get(2), 0) != 'D') || (getCharFromString(tab.get(3), 0)) != 'E') {
                warningError = 2; // error 2 if incorrect tuning letter
            }
        } else if (instrument == 3) {// Drum
            // check all lines have the same length
            for (int i = 0; i < tab.size() - 1; i++) {
                if (tab.get(i).length() != tab.get(i + 1).length()) {
                    warningError = 1; // error 1 if all lines are not the same length
                }
            }
        }

        System.out.println("Text Reader Error: " + warningError);
        return warningError;
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
                + "note 2 :" + obj.getnote2() + "\t" + "Technique :" + obj.gettech() + "\t" + "i :" + obj.getindex()));
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
                    if (oldNumDash == numDash);
                    else return -1;
                }
            }
        }

        return oldNumDash;
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

            if(noteDur % 2 == 1 ){ // if odd then return 1
                return 1;
            }
            else if(minNoteDur > noteDur && noteDur != 0){
                minNoteDur = noteDur;
            }

            prevNote = note;
        }

        return minNoteDur;
    }

}