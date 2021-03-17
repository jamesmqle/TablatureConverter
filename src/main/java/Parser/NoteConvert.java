package Parser;

/*
 * NoteConvert Java Class
 *
 * This class contains a bunch of functions relevant to converting guitar tablature
 * into notes and their octaves.
 *
 */

import XMLTags.Common.Note;
import XMLTags.Common.Pitch;

public class NoteConvert {

/*	 Takes a note and converts it into its equivalent number, from 0-12, starting at C.
     Change to 0-12 starting at C
*/
	// need to also return alters
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
		if (step%12 == 9) return "A";
		else if (step%12 == 10) return "A";
		else if (step%12 == 11) return "B";
		else if (step%12 == 0) return "C";
		else if (step%12 == 1) return "C";
		else if (step%12 == 2) return "D";
		else if (step%12 == 3) return "D";
		else if (step%12 == 4) return "E";
		else if (step%12 == 5) return "F";
		else if (step%12 == 6) return "F";
		else if (step%12 == 7) return "G";
		else if (step%12 == 8) return "G";
		else return "";
	}

	public static String intToStepFull(int step) {
		if (step%12 == 9) return "A";
		else if (step%12 == 10) return "A#";
		else if (step%12 == 11) return "B";
		else if (step%12 == 0) return "C";
		else if (step%12 == 1) return "C#";
		else if (step%12 == 2) return "D";
		else if (step%12 == 3) return "D#";
		else if (step%12 == 4) return "E";
		else if (step%12 == 5) return "F";
		else if (step%12 == 6) return "F#";
		else if (step%12 == 7) return "G";
		else if (step%12 == 8) return "G#";
		else return "";
	}

	public static int intToAlter(int step) {
		if (step%12 == 9) return 0;
		else if (step%12 == 10) return 1;
		else if (step%12 == 11) return 0;
		else if (step%12 == 0) return 0;
		else if (step%12 == 1) return 1;
		else if (step%12 == 2) return 0;
		else if (step%12 == 3) return 1;
		else if (step%12 == 4) return 0;
		else if (step%12 == 5) return 0;
		else if (step%12 == 6) return 1;
		else if (step%12 == 7) return 0;
		else if (step%12 == 8) return 1;
		else return 0;
	}

/*
      Given the information from the guitar tab, this function
        converts the guitar tablature into a note.
*/
	public static Note convertToNote(String stringTune, int tabNote) {
		int stringTuneNote, result, addOctave;
		Note finalNote = new Note();
		addOctave = 0;
		
		stringTuneNote = stepToInt(stringTune);
		
		result = stringTuneNote + tabNote; // A + 2 = 11
		
		while (result > 11) {
			addOctave++;
			result = result - 12;
		}

		finalNote.setPitch(new Pitch(intToStep(result), addOctave, intToAlter(result)));
		
		return finalNote;
	}

	public static int convertToStringInt(String stringTune) {
		if (stringTune.equals("E")) return 1;
		else if (stringTune.equals("A")) return 2;
		else if (stringTune.equals("D")) return 3;
		else if (stringTune.equals("G")) return 4;
		else if (stringTune.equals("B")) return 5;
		else if (stringTune.equals("e")) return 6;
		else return -1;
	}

	// bruh moment
	// hotfix only for standard tuning
	public static int octaveFinder(String stringTune, int tabNote) {
		int stringTuneNote, result, addOctave;
		addOctave = 0;

		if (stringTune.equals("E")) addOctave = 2;
		else if (stringTune.equals("A")) addOctave = 2;
		else if (stringTune.equals("D")) addOctave = 3;
		else if (stringTune.equals("G")) addOctave = 3;
		else if (stringTune.equals("B")) addOctave = 3;
		else if (stringTune.equals("e")) addOctave = 4;
		stringTuneNote = stepToInt(stringTune);
		
		result = stringTuneNote + tabNote;
		
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
