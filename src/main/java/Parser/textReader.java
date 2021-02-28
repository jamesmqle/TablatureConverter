package main.java.Parser;

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
		String path = "D:\\3221\\gg.txt";
		List<Output> list = new ArrayList<>();
		list = parsIt(path);
		printData(list);
	}

	public static List<Output> parsIt(String path) throws FileNotFoundException {

		List<Output> list = new ArrayList<>();
		List<String> zoom = new ArrayList<>();
		int flag = 0;

		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		String data = null;
		int k = 0;

		while (myReader.hasNextLine()) {

			// Check which instrument it is
			data = myReader.nextLine();
			if ((!data.isEmpty()) && (flag == 0)) {

				if ((data.charAt(0) == 'e') || (data.charAt(0) == 'E')) { // guitar
					flag = 1;
				} else if (data.charAt(0) == 'G') { // bass
					flag = 2;
				} else { // drum
					flag = 3;
				}
			}

			if (flag == 1) { // Guitar
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					System.out.println("****");
				} else {
					System.out.println(data);
					zoom.add(data);//
					k++;
				}
				if (k == 6) {
					try {
						TabIsOK(zoom, flag);
						list = ParsGuitar(zoom, list);
						k = 0;
						zoom.clear();

					} catch (StringIndexOutOfBoundsException e) {
						// handleException();
						System.out.println(
								"Exception occurred . . . . Strings do not have the same length or do have wrong tunning!!");
					}
				}
			} else if (flag == 2) { // Bass
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					System.out.println("****");

				} else {
					System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 4) {
					try {
						TabIsOK(zoom, flag);
						list = ParsBass(zoom, list);
						k = 0;
						zoom.clear();

					} catch (StringIndexOutOfBoundsException e) {
						// handleException();
						System.out.println(
								"Exception occurred . . . . Strings do not have the same length or they do have wrong tunning!!");
					}
				}
			} else if (flag == 3) {// Drum
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					System.out.println("****");

				} else {
					System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (data.charAt(0) == 'B') {
					try {
						TabIsOK(zoom, flag);
						list = ParsDrum(zoom, list, k);
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

		myReader.close();

		return list;

	}

	public static List<Output> ParsGuitar(List<String> zoom, List<Output> list) {
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
		}

		int length = zoom.get(0).length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < 6; j++) {

				if (i < 0 || i > length) {
					throw new IndexOutOfBoundsException("index :" + i + " but size of list:" + length);
				}

				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '-')
									|| (getCharFromString(zoom.get(j), i + 1) == '|'))) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))), -1, "-", i));

					}
					// 2 digits
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

	public static List<Output> ParsBass(List<String> zoom, List<Output> list) {
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
		}
		int length = zoom.get(0).length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < 4; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
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

	public static List<Output> ParsDrum(List<String> zoom, List<Output> list, int m) {
		if (!list.isEmpty()) {
			list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
		}
		int length = zoom.get(0).length();
		for (int i = 3; i < length; i++) {
			for (int j = 0; j < m; j++) {
				if ((getCharFromString(zoom.get(j), i) != '-') && (getCharFromString(zoom.get(j), i) != '|')) {
					// 1 digit
					list.add(new Output(
							Character.toString(getCharFromString(zoom.get(j), 0))
									+ Character.toString(getCharFromString(zoom.get(j), 1)),
							-1, -1, Character.toString(getCharFromString(zoom.get(j), i)), i));

				}
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

	// Gets character from the line given
	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

	// Print data of the output list that we return
	public static void printData(List<Output> list) {
		list.forEach(obj -> System.out.println("Tunning :" + obj.getletter() + "\t" + "note 1 :" + obj.getnote1() + "\t"
				+ "note 2 :" + obj.getnote2() + "\t" + "Technique :" + obj.gettech() + "\t" + "i :" + obj.getindex()));
	}

	private static void TabIsOK(List<String> zoom, int flag) {
		// TODO Auto-generated method stub
		if (flag == 1) {// Guitar

			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
			if ((getCharFromString(zoom.get(1), 0) != 'B') || (getCharFromString(zoom.get(2), 0) != 'G')
					|| (getCharFromString(zoom.get(3), 0) != 'D') || (getCharFromString(zoom.get(4), 0) != 'A')
					|| (getCharFromString(zoom.get(5), 0) != 'D')) {
				throw new StringIndexOutOfBoundsException();
			}
			

		} else if (flag == 2) {// Bass
			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
			if ((getCharFromString(zoom.get(1), 0) != 'D') || (getCharFromString(zoom.get(2), 0) != 'A')
					|| (getCharFromString(zoom.get(3), 0) != 'D')) {
				throw new StringIndexOutOfBoundsException();
			}
		} else if (flag == 3) {// Drum
			for (int i = 0; i < zoom.size() - 1; i++) {
				if (zoom.get(i).length() != zoom.get(i + 1).length()) {
					throw new StringIndexOutOfBoundsException();
				}
			}
		}
	}

	/*
	 * private static void handleException() { Alert errorAlert = new
	 * Alert(Alert.AlertType.ERROR); errorAlert.setHeaderText("File not found.");
	 * errorAlert.setContentText("Please choose a file first before you preview");
	 * errorAlert.showAndWait(); }
	 */

}