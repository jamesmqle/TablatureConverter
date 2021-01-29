import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class textReader {
	public static void main(String[] args) {
		Scanner in = new Scanner(new FileReader("filename.txt"));
		BufferedReader br = new BufferedReader(new FileReader("hotCrossBunsGuitarTab.txt"));
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		} 
		finally {
		    br.close();
		}
	}
}
