package Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * TODO: 	1. Calculate how many dashes from first note to end of measure
 * 2. Calculate the shortest note duration (but return 1 if any note duration is odd)
 */

public class textReader extends Output {

	public textReader(String string, int n1, int n2, String tech, int i) {
		super(string, n1, n2, tech, i);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// Define the pathfile to parse
		String path = "src/main/resources/sample/LetHerGoTest.txt";
		String instrument;
		// Define a List of Output to write the result
		List<Output> list = new ArrayList<>();
		// Call the readTabFile function to parse and return the result
		list = readTabFile2(path);
		// Call printData function to print and check the list of Output which is our
		// result
		printData(list);
		if (detectInstrument(path) == 1) instrument = "XMLTags/Guitar";
		else if (detectInstrument(path) == 2) instrument = "Bass";
		else if (detectInstrument(path) == 3) instrument = "XMLTags/Drums";
		else instrument = "Failure";
		System.out.println(instrument);
	}

	public static int detectInstrument(String path) throws FileNotFoundException {
		List<Output> list = new ArrayList<>();
		List<String> zoom = new ArrayList<>();
		int flag = 0;

		Scanner myReader = new Scanner(new FileReader(path));
		String data = null;
		int k = 0;

		while (myReader.hasNextLine()) {
			// Check which instrument it is
			data = myReader.nextLine().trim();
			if ((!data.isEmpty()) && (flag == 0)) {
				// It is guitar if it starts with "e" or "E"
				if ((data.charAt(0) == 'e') || (data.charAt(0) == 'E')) { // guitar
					flag = 1;
				} else if (data.charAt(0) == 'G') { // bass
					flag = 2;
				} else { // drum
					flag = 3;
				}
			}
		}
		return flag;
	}

	public static List<Output> readTabFile(String path) throws FileNotFoundException {
		List<Output> list = new ArrayList<>();
		List<String> zoom = new ArrayList<>();
		int flag = 0;

		// scan the txt file
		Scanner myReader = new Scanner(new FileReader(path));
		String data = null;
		int k = 0;

		while (myReader.hasNextLine()) {
			// Check which instrument it is
			data = myReader.nextLine().trim();
			// Check which instrument it is (Guitar, Bass, Drum)
			if ((!data.isEmpty()) && (flag == 0)) {
				if ((data.charAt(0) == 'e') || (data.charAt(0) == 'E')) { // guitar
					flag = 1;
					// It is Bass if it starts with "G"
				} else if (data.charAt(0) == 'G') { // bass
					flag = 2;
					// It is Bass otherwise
				} else { // drum
					flag = 3;
				}
			}

			/*
			 * If instrument is guitar it adds lines of tab to the tab list until if ends
			 * which is after 6 strings or lines
			 */
			if (flag == 1) { // XMLTags.Guitar
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					//System.out.println("****");
				} else {
					//System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 6) {
					list = ParseGuitar(zoom, list);
					k = 0;
					zoom.clear();
				}
				/*
				 * If instrument is bass it adds lines of tab to the tab list until if ends
				 * which is after 4 strings or lines
				 */
			} else if (flag == 2) { // Bass
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					//System.out.println("****");

				} else {
					//System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 4) {
					// first handle exceptions and check if it is correct tab, then call the
					// parsBass function
					try {
						TabIsOK(zoom, flag);
						list = ParseBass(zoom, list);
						k = 0;
						zoom.clear();

					} catch (StringIndexOutOfBoundsException e) {
						// handleException();
						System.out.println(
								"Exception occurred . . . . Strings do not have the same length or they do have wrong tunning!!");
					}
				}
				/*
				 * If instrument is bass it adds lines of tab to the tab list until if ends
				 * which is after getting the last line that starts with "B"
				 */
			} else if (flag == 3) {// Drum
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					// System.out.println("****");

				} else {
					// System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (data.charAt(0) == 'B') {
					// first handle exceptions and check if it is correct tab, then call the
					// parsDrum function
					try {
						TabIsOK(zoom, flag);
						list = ParseDrum(zoom, list, k);
						k = 0;
						zoom.clear();

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

	/*
	 * "readTabFile" Takes the path of the file and reads it to recognize the
	 * instrument and call the proper instrument parser function. returns the list
	 * of output which is the result
	 */
	public static List<Output> readTabFile2(String path) throws FileNotFoundException {

		// define the result list
		// and list of string which is a single tab
		List<Output> list = new ArrayList<>();
		List<String> zoom = new ArrayList<>();
		int flag = 0;

		// scan the txt file
		Scanner myReader = new Scanner(new FileReader(path));
		String data = null;
		int k = 0;

		// while loop which is for each line of the file and stops if there is no line
		// anymore
		while (myReader.hasNextLine()) {

			// assing new line of the file into data variable which is string
			data = myReader.nextLine().trim();
			// Check which instrument it is (Guitar, Bass, Drum)
			if ((!data.isEmpty()) && (flag == 0)) {
				// It is guitar if it starts with "e" or "E"
				if ((data.charAt(0) == 'e') || (data.charAt(0) == 'E')) { // guitar
					flag = 1;
					// It is Bass if it starts with "G"
				} else if (data.charAt(0) == 'G') { // bass
					flag = 2;
					// It is Bass otherwise
				} else { // drum
					flag = 3;
				}
			}

			/*
			 * If instrument is guitar it adds lines of tab to the tab list until if ends
			 * which is after 6 strings or lines
			 */
			if (flag == 1) { // XMLTags.Guitar
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					//System.out.println("****");
				} else {
					//System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 6) {
					// first handle exceptions and check if it is correct tab, then call the
					// parsGuitar function
					try {
						TabIsOK(zoom, flag);
						list = ParseGuitar2(zoom, list);
						k = 0;
						zoom.clear();

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
			} else if (flag == 2) { // Bass
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					//System.out.println("****");

				} else {
					//System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 4) {
					// first handle exceptions and check if it is correct tab, then call the
					// parsBass function
					try {
						TabIsOK(zoom, flag);
						list = ParseBass(zoom, list);
						k = 0;
						zoom.clear();

					} catch (StringIndexOutOfBoundsException e) {
						// handleException();
						System.out.println(
								"Exception occurred . . . . Strings do not have the same length or they do have wrong tunning!!");
					}
				}
				/*
				 * If instrument is bass it adds lines of tab to the tab list until if ends
				 * which is after getting the last line that starts with "B"
				 */
			} else if (flag == 3) {// Drum
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					// System.out.println("****");

				} else {
					// System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (data.charAt(0) == 'B') {
					// first handle exceptions and check if it is correct tab, then call the
					// parsDrum function
					try {
						TabIsOK(zoom, flag);
						list = ParseDrum(zoom, list, k);
						k = 0;
						zoom.clear();

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


	/*
	 * "getTab" Takes the path of the file and reads it and return
	 * the tab as a list of string
	 */
	public static List<String> getTab(String path) throws FileNotFoundException {

		// define the result list
		// and list of string which is a single tab
		List<String> tab = new ArrayList<>();

		// scan the txt file
		Scanner myReader = new Scanner(new FileReader(path));
		String data = null;

		while (myReader.hasNextLine()) {

			// assigning new line of the file into data variable which is string
			data = myReader.nextLine().trim();
			tab.add(data);
		}
		return tab;
	}

	/*
	 needs comment
	 */
	public static List<Output> ParseGuitar(List<String> zoom, List<Output> list) {
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -2, -2, "-", -2));
		}
		int length = zoom.get(0).length();
		for (int i = 2; i < length - 1; i++) { //changed so that the final new measure output isnt detected - tuan feb 28
			for (int j = 0; j < 6; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					// Check if the note is proper 1 digit to add new tab element to the list
					if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '-')
							|| (getCharFromString(zoom.get(j), i + 1) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))), -1, "-", i));

					}
					// 2 digits
					// Check if the note is proper 2 digit to add new tab element to the list
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 2) == '-')
							|| (getCharFromString(zoom.get(j), i + 2) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) != '-')
							|| (getCharFromString(zoom.get(j), i + 1) != '|'))) {
						list.add(
								new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
										Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
												+ Character.toString(getCharFromString(zoom.get(j), i + 1))),
										-1, "-", i));

					}

					// 3 digit with technique
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '/')
							|| (getCharFromString(zoom.get(j), i + 1) == 's')
							|| (getCharFromString(zoom.get(j), i + 1) == 'p')
							|| (getCharFromString(zoom.get(j), i + 1) == 'h'))
							&& ((getCharFromString(zoom.get(j), i + 2) != '-')
							|| (getCharFromString(zoom.get(j), i + 2) != '|'))
							&& ((getCharFromString(zoom.get(j), i + 3) == '-')
							|| (getCharFromString(zoom.get(j), i + 3) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
					}
				}
				// Check if the element is "|" to add new tab element to the list
				if (getCharFromString(zoom.get(j), i) == '|') {
					list.add(new Output("*New Measure*", -1, -1, "-", i));
					if (i != length - 1) {
						i++;
					} else {
						break;
					}
				}
			}
		}
		return list;
	}

	// reads to end of file
	/*
	 * "ParsGuitar" Takes the tab to parse and the list of the results so far. Then
	 * makes changes and adds new elements to the list. returns the result list of
	 * Output.
	 */
	public static List<Output> ParseGuitar2(List<String> zoom, List<Output> list) {
		// Check if the list is empty to add new tab element to the list
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -2, -2, "-", -2));
		}
		int length = zoom.get(0).length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < 6; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					// Check if the note is proper 1 digit to add new tab element to the list
					if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '-')
							|| (getCharFromString(zoom.get(j), i + 1) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))), -1, "-", i));

					}
					// 2 digits
					// Check if the note is proper 2 digit to add new tab element to the list
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 2) == '-')
							|| (getCharFromString(zoom.get(j), i + 2) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) != '-')
							|| (getCharFromString(zoom.get(j), i + 1) != '|'))) {
						list.add(
								new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
										Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
												+ Character.toString(getCharFromString(zoom.get(j), i + 1))),
										-1, "-", i));

					}
					// 3 digits with technique
					// Check if the note is proper 3 digit to add new tab element to the list
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '/')
							|| (getCharFromString(zoom.get(j), i + 1) == 's')
							|| (getCharFromString(zoom.get(j), i + 1) == 'p')
							|| (getCharFromString(zoom.get(j), i + 1) == 'h'))
							&& ((getCharFromString(zoom.get(j), i + 2) != '-')
							|| (getCharFromString(zoom.get(j), i + 2) != '|'))
							&& ((getCharFromString(zoom.get(j), i + 3) == '-')
							|| (getCharFromString(zoom.get(j), i + 3) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
					}
				}
				// Check if the element is "|" to add new tab element to the list
				if (getCharFromString(zoom.get(j), i) == '|') {
					list.add(new Output("*New Measure*", -1, -1, "-", i));
					if (i != length - 1) {
						i++;
					} else {
						break;
					}
				}
			}
		}
		return list;
	}

	/*
	 * "ParsBass" Takes the tab to parse and the list of the results so far. Then
	 * makes changes and adds new elements to the list. returns the result list of
	 * Output.
	 */
	public static List<Output> ParseBass(List<String> zoom, List<Output> list) {
		// Check if the list is empty to add new tab element to the list
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -2, -2, "-", -2));
		}
		int length = zoom.get(0).length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < 4; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					// Check if the note is proper 1 digit to add new tab element to the list
					if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '-')
							|| (getCharFromString(zoom.get(j), i + 1) == '|'))) {
						if (isInteger(Character.toString(getCharFromString(zoom.get(j), i)))) {
							list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
									Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))), -1, "-",
									i));
						} else {
							list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)), -1, -1,
									Character.toString(getCharFromString(zoom.get(j), i)), i));
						}

					}
					// 2 digits
					// Check if the note is proper 2 digit to add new tab element to the list
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 2) == '-')
							|| (getCharFromString(zoom.get(j), i + 2) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) != '-')
							|| (getCharFromString(zoom.get(j), i + 1) != '|'))) {
						list.add(
								new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
										Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
												+ Character.toString(getCharFromString(zoom.get(j), i + 1))),
										-1, "-", i));

					}
					// 3 digits number in parentheses like (0)
					// Check if the note is proper 3 digit to add new tab element to the list
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '/')
							&& (getCharFromString(zoom.get(j), i + 1) != 's')
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
										+ Character.toString(getCharFromString(zoom.get(j), i + 1))
										+ Character.toString(getCharFromString(zoom.get(j), i + 2))),
								-1, "-", i));
					}

					// 3 digit with technique
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '/')
							|| (getCharFromString(zoom.get(j), i + 1) == 's')
							|| (getCharFromString(zoom.get(j), i + 1) == 'p')
							|| (getCharFromString(zoom.get(j), i + 1) == 'h'))
							&& ((getCharFromString(zoom.get(j), i + 2) != '-')
							|| (getCharFromString(zoom.get(j), i + 2) != '|'))
							&& ((getCharFromString(zoom.get(j), i + 3) == '-')
							|| (getCharFromString(zoom.get(j), i + 3) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
					}
				}
				// Check if the element is "|" to add new tab element to the list
				if (getCharFromString(zoom.get(j), i) == '|') {
					list.add(new Output("*New Measure*", -1, -1, "-", i));
					if (i != length - 1) {
						i++;
					} else {
						break;
					}
				}
			}
		}
		return list;
	}

	/*
	 * "ParsDrum" Takes the tab to parse and the list of the results so far. Then
	 * makes changes and adds new elements to the list. returns the result list of
	 * Output.
	 */
	public static List<Output> ParseDrum(List<String> zoom, List<Output> list, int m) {
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
		}
		int length = zoom.get(0).length();
		for (int i = 3; i < length; i++) {
			for (int j = 0; j < m; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					// Check if the note is proper 1 digit to add new tab element to the list
					list.add(new Output(
							Character.toString(getCharFromString(zoom.get(j), 0))
									+ Character.toString(getCharFromString(zoom.get(j), 1)),
							-1, -1, Character.toString(getCharFromString(zoom.get(j), i)), i));

				}
				// Check if the element is "|" to add new tab element to the list
				if (getCharFromString(zoom.get(j), i) == '|') {
					list.add(new Output("*New Measure*", -1, -1, "-", i));
					if (i != length - 1) {
						i++;
					} else {
						break;
					}
				}
			}
		}
		return list;
	}

	/*
	 * "TabIsOK" Takes the tab and flag which tells the instrument. check the tab if
	 * it is correct or not. throw an exception if something is wrong
	 */
	public static int TabIsOK(List<String> tab, int flag) {

		int error = 0;

		System.out.println("Flag: " + flag);
		if (flag == 1) {// Guitar
			// check all lines have the same length
			for (int i = 0; i < tab.size() - 1; i++) {
				if (tab.get(i).length() != tab.get(i + 1).length()) {
					error = 1; // error 1 if all lines are not the same length
				}
			}
			System.out.println("Character: " + Character.toLowerCase(getCharFromString(tab.get(0), 0) ));
			// check all lines have the correct tuning letter
			if ((Character.toLowerCase(getCharFromString(tab.get(0), 0) ) != 'e') || (getCharFromString(tab.get(1), 0) != 'B') || (getCharFromString(tab.get(2), 0) != 'G')
					|| (getCharFromString(tab.get(3), 0) != 'D') || (getCharFromString(tab.get(4), 0) != 'A')
					|| (getCharFromString(tab.get(5), 0) != 'E')) {
				error = 2; // error 2 if incorrect tuning letter
			}

		} else if (flag == 2) {// Bass
			// check all lines have the same length
			for (int i = 0; i < tab.size() - 1; i++) {
				if (tab.get(i).length() != tab.get(i + 1).length()) {
					error = 1; // error 1 if all ines are not the same length
				}
			}
			// check all lines have the correct tuning letter
			if ((getCharFromString(tab.get(1), 0) != 'D') || (getCharFromString(tab.get(2), 0) != 'A')
					|| (getCharFromString(tab.get(3), 0) != 'D')) {
				error = 2; // error 2 if incorrect tuning letter
			}
		} else if (flag == 3) {// Drum
			// check all lines have the same length
			for (int i = 0; i < tab.size() - 1; i++) {
				if (tab.get(i).length() != tab.get(i + 1).length()) {
					error = 1; // error 1 if all lines are not the same length
				}
			}
		}

		System.out.println("Text Reader Error: " + error);
		return error;
	}

	/*
	 * "isInteger" Takes the string and return the boolean true if the string is an
	 * integer false if the string is not an integer
	 */
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer

			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
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
	 * it is currently 4:20 am and my eyes are slowly closing
	 *  1. Calculate how many dashes from first note to end of measure
	 *	2. Calculate the shortest note duration (but return 1 if any note duration is odd)
	 **/

	public static int numberOfDashes(String filepath) throws FileNotFoundException {
		List<Output> list = new ArrayList<>();
		list = readTabFile2(filepath);
		/*        printData(list);*/
		int firstIndex = -1, measureIndex = -1, numDash = -1, oldNumDash = -1;
		int counter = 0, isFirst = 0;

		// 1. get first measure index
		// 2. get measure end index
		// 3. repeat for every measure

		for (Output note : list){
			//System.out.println("counter: " + counter);
			if (counter == 0 && (note.getnote1() != -1 && note.getnote1() != -2) ) {
				firstIndex = note.getindex();
				counter++;
			}
			if (note.getnote1() == -1) {
				counter = 0;
				measureIndex = note.getindex();
				//System.out.println("measureIndex: " + measureIndex);
				if (isFirst == 0) {
					oldNumDash = measureIndex - firstIndex;
					//System.out.println("oldNumDash: " + oldNumDash);
					isFirst++;
				} else {
					numDash = measureIndex - firstIndex;
					//System.out.println("numDash: " + numDash);
					//System.out.println("ond = nd " + (oldNumDash == numDash));
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
		list = readTabFile2(filepath);
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