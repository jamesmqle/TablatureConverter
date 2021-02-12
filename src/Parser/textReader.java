package Parser;

import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*

 */
public class textReader {

	/*
	 *	Todo:
	 *  Make this a function that accepts filepath as a string
	 */

	public static void main(String[] args) throws IOException {
		String filepath;
		Scanner in = new Scanner(new FileReader("C:\\Users\\tukau\\Documents\\GitHub\\TablatureConverter\\src\\sample\\hotCrossBunsGuitarTab"));
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tukau\\Documents\\GitHub\\TablatureConverter\\src\\sample\\hotCrossBunsGuitarTab"));
		
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

		   // toVerticalWords(everything);


/*			Pattern pt = Pattern.compile("\\d");
			Matcher mt = pt.matcher(everything);
			while(mt.find()){
				System.out.print(mt.group());
			}*/
		} 
		finally {
		    br.close();
		}

	}

	/*// This method will make the String vertical
	private static void toVerticalWords(String str) {
		//split the words by whitespace
		String[] strArr = str.split("\\s");
		int maxWordLen = 0;

		//get the longest word length
		for(String strTemp : strArr) {
			if(strTemp.length() > maxWordLen)
				maxWordLen = strTemp.length();
		}

		//make a matrix of the words with each character in an array block
		char[][] charArr = new char[strArr.length][maxWordLen];
		for(int i=0; i<strArr.length; i++) {
			int j=0;
			for(char ch : strArr[i].toCharArray()){
				charArr[i][j] = ch;
				j++;
			}
		}

		//print the vertical word pattern, or transpose of above matrix (2D array)
		for(int j=0; j<maxWordLen; j++) {
			for(int i=0; i<strArr.length; i++) {
				if (i!=0)
					System.out.print(" ");
				System.out.print(charArr[i][j]);
			}

			System.out.println();
		}
	}*/





}