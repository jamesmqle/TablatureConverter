package Parser;

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
		// public static List<Output> main(String[] args) throws IOException{

		List<Output> list = new ArrayList<>();
		List<String> zoom = new ArrayList<>();
		int flag = 0;

		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		String data = null;
		int k = 0;
		int m = 0;

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
				} // Drum
			} else if (flag == 3) {
				if ((data.isEmpty()) || (data.charAt(0) == ' ')) {
					System.out.println("****");
					m = 1;

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

		printData(list);
		// return list;
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

					}
					// 3 digits number
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
						// i += 2;
					}
					// 3 digit with technique
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& ((getCharFromString(zoom.get(j), i + 1) == '/')
									|| (getCharFromString(zoom.get(j), i + 1) == 's'))
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
						// i += 2;
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

	// Gets character from the line given
	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

	// Print data of the output list that we return
	public static void printData(List<Output> list) {
		list.forEach(obj -> System.out.println("Tunning :" + obj.getletter() + "\t" + "note 1 :" + obj.getnote1() + "\t"
				+ "note 2 :" + obj.getnote2() + "\t" + "Technique :" + obj.gettech() + "\t" + "i :" + obj.getindex()));
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
						// i++;

					}
					// 3 digits number
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '/')
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
										+ Character.toString(getCharFromString(zoom.get(j), i + 1))
										+ Character.toString(getCharFromString(zoom.get(j), i + 2))),
								-1, "-", i));
						// i += 2;
					}
					// 3 digit with technique
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& (getCharFromString(zoom.get(j), i + 1) == '/')
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
						// i += 2;
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
					if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) == '-')
									|| (getCharFromString(zoom.get(j), i + 1) == '|'))) {
						list.add(new Output(
								Character.toString(getCharFromString(zoom.get(j), 0))
										+ Character.toString(getCharFromString(zoom.get(j), 1)),
								-1, -1, Character.toString(getCharFromString(zoom.get(j), i)), i));

					}
					// 2 digits
					else if (((getCharFromString(zoom.get(j), i - 1) == '-')
							|| (getCharFromString(zoom.get(j), i - 1) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 2) == '-')
									|| (getCharFromString(zoom.get(j), i + 2) == '|'))
							&& ((getCharFromString(zoom.get(j), i + 1) != '-')
									|| (getCharFromString(zoom.get(j), i + 1) != '|'))) {
						list.add(new Output(
								Character.toString(getCharFromString(zoom.get(j), 0))
										+ Character.toString(getCharFromString(zoom.get(j), 1)),
								-1, -1, Character.toString(getCharFromString(zoom.get(j), i))
										+ Character.toString(getCharFromString(zoom.get(j), i)),
								i));
						// i++;

					}
					// 3 digits number
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '-')
							&& (getCharFromString(zoom.get(j), i + 1) != '/')
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))
										+ Character.toString(getCharFromString(zoom.get(j), i + 1))
										+ Character.toString(getCharFromString(zoom.get(j), i + 2))),
								-1, "-", i));
						// i += 2;
					}
					// 3 digit with technique
					else if ((getCharFromString(zoom.get(j), i - 1) == '-')
							&& (getCharFromString(zoom.get(j), i + 1) == '/')
							&& (getCharFromString(zoom.get(j), i + 2) != '-')
							&& (getCharFromString(zoom.get(j), i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(zoom.get(j), 0)),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i))),
								Integer.parseInt(Character.toString(getCharFromString(zoom.get(j), i + 2))),
								Character.toString(getCharFromString(zoom.get(j), i + 1)), i));
						// i += 2;
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

}