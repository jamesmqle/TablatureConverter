package Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class textReader extends Output {

	public textReader(String string, int string2, int i) {
		super(string, string2, i);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// public static List<Output> main(String[] args) throws IOException{

		List<Output> list = new ArrayList<>();

		String arr[] = new String[10];
		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		String data = null;
		int k = 0;
		//Check the number of lines and print it
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			System.out.println(data);
			arr[k] = data;
			k++;
		}
		
		myReader.close();
		int length = data.length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < k; j++) {
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
						// i++;

					}
					// 3 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) != '-') && (getCharFromString(arr[j], i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))
										+ Character.toString(getCharFromString(arr[j], i + 2))),
								i));
						// i += 2;
					}
				}
				if(getCharFromString(arr[j], i) == '|') {
					list.add(new Output("*New Measure*",-1,i));
					if(i!=length-1) {
						i++;
					}
					else {
						break;
					}
				}
			}
		}
		printData(list);
		// return list;
	}

	public static void readTabFile(String filepath) throws IOException {
		// public static List<Output> main(String[] args) throws IOException{

		List<Output> list = new ArrayList<>();

		String arr[] = new String[10];
		Scanner myReader = new Scanner(new FileReader(filepath));
		String data = null;
		int k = 0;
		//Check the number of lines and print it
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			System.out.println(data);
			arr[k] = data;
			k++;
		}

		myReader.close();
		int length = data.length();
		for (int i = 2; i < length; i++) {
			for (int j = 0; j < k; j++) {
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
						// i++;

					}
					// 3 digits
					else if ((getCharFromString(arr[j], i - 1) == '-') && (getCharFromString(arr[j], i + 1) != '-')
							&& (getCharFromString(arr[j], i + 2) != '-') && (getCharFromString(arr[j], i + 3) == '-')) {
						list.add(new Output(Character.toString(getCharFromString(arr[j], 0)),
								Integer.parseInt(Character.toString(getCharFromString(arr[j], i))
										+ Character.toString(getCharFromString(arr[j], i + 1))
										+ Character.toString(getCharFromString(arr[j], i + 2))),
								i));
						// i += 2;
					}
				}
				if(getCharFromString(arr[j], i) == '|') {
					list.add(new Output("*New Measure*",-1,i));
					if(i!=length-1) {
						i++;
					}
					else {
						break;
					}
				}
			}
		}
		printData(list);
		// return list;
	}

	// Gets character from the line given
	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

	// Print data of the output list that we return
	public static void printData(List<Output> data) {
		data.forEach(obj -> System.out.println(
				"Tunning :" + obj.getLetter() + "\t" + "fret num :" + obj.getNum() + "\t" + "i :" + obj.getIndex()));
	}

}