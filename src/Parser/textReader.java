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
		// TODO Auto-generated constructor stub
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
					list = ParsGuitar(zoom, list);
					k = 0;
					zoom.clear();
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
					list = ParsBass(zoom, list);
					k = 0;
					zoom.clear();
				}
			} else if (flag == 3) {// Drum
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					System.out.println("****");
					// m = 1;

				} else {
					System.out.println(data);
					zoom.add(data);
					k++;
				}
				if (k == 6) {
					list = ParsDrum(zoom, list, k);
					k = 0;
					zoom.clear();
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
}