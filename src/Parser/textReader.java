package Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import static GUI.Controller.fileAsString;

public class textReader extends Output {

	public textReader(String string, int string2, int i) {
		super(string, string2, i);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// public static List<Output> main(String[] args) throws IOException{
/*
		List<Output> list = new ArrayList<>();

		String arr[] = new String[6];
		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		for (int i = 0; i < 6; i++) {
			String data = myReader.nextLine();
			System.out.println(data);
			arr[i] = data;
		}

		myReader.close();
		for (int i = 2; i < 60; i++) {
			for (int j = 0; j < 6; j++) {
				if ((getCharFromString(arr[j], i) != '-') && (getCharFromString(arr[j], i) != '|')) {
					// 1 digit
					if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))), i));
					}
					// 2 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))),
								i));
						i++;

					}
					// 3 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) != '-') && (getCharFromString(arr[j], i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))
										+ Character.toString(getCharFromString(arr[j], i + 2))),
								i));
						i += 2;
					}
				}
			}
		}
		printData(list);
		// return list;*/

		readTabFile("");
	}

	public static List<Output> readTabFile(String filepath) throws IOException {
		// public static List<Output> main(String[] args) throws IOException{

		List<Output> list = new ArrayList<>();

		String arr[] = new String[6];
		Scanner myReader = new Scanner(new FileReader(fileAsString));

		for (int i = 0; i < 6; i++) {
			String data = myReader.nextLine();
			System.out.println(data);
			arr[i] = data;
		}

		myReader.close();

		for (int i = 2; i < arr[0].length(); i++) {
			for (int j = 0; j < 6; j++) {
				if ((getCharFromString(arr[j], i) != '-') && (getCharFromString(arr[j], i) != '|')) {
					// 1 digit
					if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))), i));
					}
					// 2 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))),
								i));
						i++;

					}
					// 3 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) != '-') && (getCharFromString(arr[j], i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))
										+ Character.toString(getCharFromString(arr[j], i + 2))),
								i));
						i += 2;
					}
				}
			}
		}
		printData(list);
		return list;
	}

	// Gets character from the line given
	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

	// Print data of the output list that we return
	public static void printData(List<Output> data) {
		data.forEach(obj -> System.out.println(
				"Tuning:	" + obj.getletter() + "\t" + "fret:	" + obj.getnum() + "\t" + "i:	" + obj.getindex()));
	}

}