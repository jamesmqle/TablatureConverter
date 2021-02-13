package Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files


public class textReader {

	public static void main(String[] args) throws IOException{
		
		 class Tuple<String, String1, Integer> {
		    public Tuple(String1 string, String1 string2, int i) {
				// TODO Auto-generated constructor stub
		    	First=string;
		    	Second= string2;
		    	Third=i;
			}
			public String1 First;
		    public String1 Second;
		    public int Third;    
		}
		 
		List<Tuple<String, String, Integer>> list = null;
		 
		list.add(new Tuple<String, String, Integer>("hello","jh", 1));
		 
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
						//list.add(Character.toString(getCharFromString(arr[j],0)),Character.toString(getCharFromString(arr[j],i)),j);
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
						i+=2;
					}
				}
			}
		}

	}

	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}
	
	
}