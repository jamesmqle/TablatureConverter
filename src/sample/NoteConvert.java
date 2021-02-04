package sample;

public class NoteConvert {
	
	public static class Note {
		private char step;
		private int alter;
	}
	
	public static int stepToInt(String step) {
		if (step.toLowerCase().equals("a")) return 9;
		else if (step.toLowerCase().equals("a#")) return 10;
		else if (step.toLowerCase().equals("b")) return 2;
		else if (step.toLowerCase().equals("c")) return 3;
		else if (step.toLowerCase().equals("c#")) return 4;
		else if (step.toLowerCase().equals("d")) return 5;
		else if (step.toLowerCase().equals("d#")) return 6;
		else if (step.toLowerCase().equals("e")) return 7;
		else if (step.toLowerCase().equals("f")) return 8;
		else if (step.toLowerCase().equals("f#")) return 9;
		else if (step.toLowerCase().equals("g")) return 10;
		else if (step.toLowerCase().equals("g#")) return 11;
		else return -1;
	}
	
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
	
	public static void noteConcat(String step, int octave) {
		System.out.println(convertToNote(step, octave) + " " + octaveFinder(step, octave));
	}
	
	public static void main(String[] args) {
		noteConcat("E", 1);
	}

}
