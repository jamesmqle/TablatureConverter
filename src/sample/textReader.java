package sample;

import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class textReader {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("C:\\Users\\James Le\\IdeaProjects\\TablatureConverter\\src\\sample\\hotCrossBunsGuitarTab"));
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\James Le\\IdeaProjects\\TablatureConverter\\src\\sample\\hotCrossBunsGuitarTab"));
		
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

			Pattern pt = Pattern.compile("\\d");
			Matcher mt = pt.matcher(everything);
			while(mt.find()){
				System.out.print(mt.group());
			}

		} 
		finally {
		    br.close();
		}

	}
}