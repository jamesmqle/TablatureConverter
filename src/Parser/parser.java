package Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class parser {

	public static void main(String[] args) throws IOException {
		int jCopy = 0;
		boolean underClear=true;
		String arr[] = new String[6];
		Scanner myReader = new Scanner(new FileReader("C:\\Users\\James Le\\IdeaProjects\\TablatureConverter\\src\\sample\\hotCrossBunsGuitarTab"));
		for (int i = 0; i < 6; i++) {
			String data = myReader.nextLine();
			System.out.println(data);
			arr[i] = data;
		}
		myReader.close();

		for(int i=2;i<64;i++) {
			for(int j=0;j<6;j++) {
				if((getCharFromString(arr[j],i)!='-') && (getCharFromString(arr[j],i)!='|')) {
					if(getCharFromString(arr[j],i+1)=='-' || getCharFromString(arr[j],i+1) == '|') {
						System.out.println(getCharFromString(arr[j],i));
					}
					else if(getCharFromString(arr[j],i+1) != '-' || getCharFromString(arr[j],i+1) != '|'){
						System.out.println(Character.toString(getCharFromString(arr[j],i))+Character.toString(getCharFromString(arr[j],i+1)));
						i++;
					}
				}
			}
		}
	}
	
	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

}
