package Parser;

/*
 * NoteConvert Java Class
 *
 * This class contains a bunch of functions relevant to converting guitar tablature
 * into notes and their octaves.
 *
 */

public class NoteConvert {
	
	public static class Note {
		private char step;
		private int alter;
	}

/*	 Takes a note and converts it into its equivalent number, from 0-12, starting at C.
     Change to 0-12 starting at C
*/
	public static int stepToInt(String step) {
		if (step.toLowerCase().equals("a")) return 9;
		else if (step.toLowerCase().equals("a#")) return 10;
		else if (step.toLowerCase().equals("b")) return 11;
		else if (step.toLowerCase().equals("c")) return 0;
		else if (step.toLowerCase().equals("c#")) return 1;
		else if (step.toLowerCase().equals("d")) return 2;
		else if (step.toLowerCase().equals("d#")) return 3;
		else if (step.toLowerCase().equals("e")) return 4;
		else if (step.toLowerCase().equals("f")) return 5;
		else if (step.toLowerCase().equals("f#")) return 6;
		else if (step.toLowerCase().equals("g")) return 7;
		else if (step.toLowerCase().equals("g#")) return 8;
		else return -1;
	}

	// Converts our mathed up number back to its note as a string
	public static String intToStep(int step) {
		if (step == 9) return "a";
		else if (step == 10) return "a#";
		else if (step == 11) return "b";
		else if (step == 0) return "c";
		else if (step == 1) return "c#";
		else if (step == 2) return "d";
		else if (step == 3) return "d#";
		else if (step == 4) return "e";
		else if (step == 5) return "f";
		else if (step == 6) return "f#";
		else if (step == 7) return "g";
		else if (step == 8) return "g#";
		else return "error";
	}

/*
      Given the information from the guitar tab, this function
        converts the guitar tablature into a note.
*/
	public static String convertToNote(String stringTune, int tabNote) {
		int stringTuneNote, result, addOctave;
		String finalNote;
		addOctave = 0;
		
		stringTuneNote = stepToInt(stringTune);
		
		result = stringTuneNote + tabNote; // 12 -> a+
		
		while (result > 11) {
			addOctave++;
			result = result - 12;
		}		
		
		finalNote = intToStep((stringTuneNote + tabNote) % 12);
		
		return finalNote;
	}

	// Finds the octave of a note on the guitar tablature.
	public static int octaveFinder(String stringTune, int tabNote) {
		int stringTuneNote, result, addOctave;
		addOctave = 0;
		
		stringTuneNote = stepToInt(stringTune);
		
		result = stringTuneNote + tabNote; // 12 -> a+
		
		while (result > 11) {
			addOctave++;
			result = result - 12;
		}
		
		return addOctave;
	}

	/*
	 * Same as convert to note function, but also returns the octave of the note
	 * along with its octave.
	 */
	public static void noteConcat(String step, int octave) {
		System.out.println(convertToNote(step, octave) + " " + octaveFinder(step, octave));
	}
	
	public static void main(String[] args) {
        System.out.println(convertToNote("E", 22));
	}

}
