package Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class textReader {

	public static void main(String[] args) throws IOException{

		String arr[] = new String[6];
		Scanner myReader = new Scanner(new FileReader("D:\\3221\\gg.txt"));
		for (int i = 0; i < 6; i++) {
			String data = myReader.nextLine();
			System.out.println(data);
			arr[i] = data;
		}
		myReader.close();
		for(int i=2;i<60;i++) {
			for(int j=0;j<6;j++) {
				if((getCharFromString(arr[j],i)!='-')&&(getCharFromString(arr[j],i)!='|')) {
					//1 digit
					if((getCharFromString(arr[j],i-1)=='-')&&(getCharFromString(arr[j],i+1)=='-')) {
						System.out.print(getCharFromString(arr[j],0));
						System.out.println(getCharFromString(arr[j],i));
					}
					//2 digits
					else if((getCharFromString(arr[j],i-1)=='-')&&(getCharFromString(arr[j],i+1)!='-')&&(getCharFromString(arr[j],i+2)=='-')) {
						System.out.print(getCharFromString(arr[j],0));

						System.out.println(Character.toString(getCharFromString(arr[j],i))+Character.toString(getCharFromString(arr[j],i+1)));
						i++;
					}
					//3 digits
					else if((getCharFromString(arr[j],i-1)=='-')&&(getCharFromString(arr[j],i+1)!='-')&&(getCharFromString(arr[j],i+2)!='-')&&(getCharFromString(arr[j],i+3)=='-')) {
						System.out.print(getCharFromString(arr[j],0));
						System.out.println(Character.toString(getCharFromString(arr[j],i))+Character.toString(getCharFromString(arr[j],i+1))+Character.toString(getCharFromString(arr[j],i+2)));
						i++;
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