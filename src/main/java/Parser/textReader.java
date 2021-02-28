package Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class textReader extends Output {

	public textReader(String string, int n1, int n2, String tech, int i) {
		super(string, n1, n2, tech, i);
	}

	public static void main(String[] args) throws IOException {
		// Define the pathfile to parse
		String path = "D:\\3221\\gg.txt";
		// Define a List of Output to write the result
		List<Output> list = new ArrayList<>();
		// Call the readTabFile function to parse and return the result
		list = readTabFile(path);
		// Call printData function to print and check the list of Output which is our
		// result
		printData(list);
	}

	/*
	 * "readTabFile" Takes the path of the file and reads it to recognize the
	 * instrument and call the proper instrument parser function. returns the list
	 * of output which is the result
	 */
	public static List<Output> readTabFile(String path) throws FileNotFoundException {

		// define the result list
		// and list of string which is a single tab
		List<Output> list = new ArrayList<>();
		List<String> tab = new ArrayList<>();
		int flag = 0;

		// scan the txt file
		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		String data = null;
		int k = 0;

		// while loop which is for each line of the file and stops if there is no line
		// anymore
		while (myReader.hasNextLine()) {

			// assing new line of the file into data variable which is string
			data = myReader.nextLine();

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
			if (flag == 1) { // Guitar
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					// System.out.println("****");
				} else {
					// System.out.println(data);
					tab.add(data);
					k++;
				}
				if (k == 6) {
					// first handle exceptions and check if it is correct tab, then call the
					// parsGuitar function
					try {
						TabIsOK(tab, flag);
						list = ParsGuitar(tab, list);
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
			} else if (flag == 2) { // Bass
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					// System.out.println("****");

				} else {
					// System.out.println(data);
					tab.add(data);
					k++;
				}
				if (k == 4) {
					// first handle exceptions and check if it is correct tab, then call the
					// parsBass function
					try {
						TabIsOK(tab, flag);
						list = ParsBass(tab, list);
						k = 0;
						tab.clear();

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
					tab.add(data);
					k++;
				}
				if (data.charAt(0) == 'B') {
					// first handle exceptions and check if it is correct tab, then call the
					// parsDrum function
					try {
						TabIsOK(tab, flag);
						list = ParsDrum(tab, list, k);
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
		return list;
	}

	/*
	 * "ParsGuitar" Takes the tab to parse and the list of the results so far. Then
	 * makes changes and adds new elements to the list. returns the result list of
	 * Output.
	 */
	public static List<Output> ParsGuitar(List<String> zoom, List<Output> list) {
		// Check if the list is empty to add new tab element to the list
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
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
						// 3 digits with technique
						// Check if the note is proper 3 digit to add new tab element to the list
					} else if (((getCharFromString(zoom.get(j), i - 1) == '-')
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
	public static List<Output> ParsBass(List<String> zoom, List<Output> list) {
		// Check if the list is empty to add new tab element to the list
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
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
	public static List<Output> ParsDrum(List<String> zoom, List<Output> list, int m) {
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
	 * "isInteger" Takes the string and return the boolean true if the string is an
	 * integer false if the string is not an integer
	 */
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);
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

	/*
	 * "TabIsOK" Takes the tab and flag which tells the instrument. check the tab if
	 * it is correct or not. throw an exception if something is wrong
	 */
	private static void TabIsOK(List<String> zoom, int flag) {
		if (flag == 1) {// Guitar
			// check all lines have the same length
			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
			// check all lines have the correct tuning letter
			if ((getCharFromString(zoom.get(1), 0) != 'B') || (getCharFromString(zoom.get(2), 0) != 'G')
					|| (getCharFromString(zoom.get(3), 0) != 'D') || (getCharFromString(zoom.get(4), 0) != 'A')
					|| (getCharFromString(zoom.get(5), 0) != 'D')) {
				throw new StringIndexOutOfBoundsException();
			}

		} else if (flag == 2) {// Bass
			// check all lines have the same length
			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
			// check all lines have the correct tuning letter
			if ((getCharFromString(zoom.get(1), 0) != 'D') || (getCharFromString(zoom.get(2), 0) != 'A')
					|| (getCharFromString(zoom.get(3), 0) != 'D')) {
				throw new StringIndexOutOfBoundsException();
			}
		} else if (flag == 3) {// Drum
			// check all lines have the same length
			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
		}
	}

	/*
	 * "handleException" handles the exception if there is a throw
	 */
	/*
	 * private static void handleException() { Alert errorAlert = new
	 * Alert(Alert.AlertType.ERROR); errorAlert.setHeaderText("File not found.");
	 * errorAlert.setContentText("Please choose a file first before you preview");
	 * errorAlert.showAndWait(); }
	 */

}