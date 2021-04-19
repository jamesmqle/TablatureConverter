package Parser;

import java.util.LinkedHashMap;
import java.util.List;

import static Parser.textReader.getCharFromString;

public class DrumParser {

    /*
     * "ParsDrum" Takes the tab to parse and the list of the results so far. Then
     * makes changes and adds new elements to the list. returns the result list of
     * Output.
     */
    public static List<Output> ParseDrum(LinkedHashMap<Integer, String> tab, List<Output> list, int m) {
        Object[] keyList = tab.keySet().toArray();
        if (!list.isEmpty()) {
            int line = (int)keyList[0];
            String tabLine = tab.get(line);
            int offset = tabLine.length()-tabLine.stripLeading().length();
            Output note = new Output("# NEW TAB #", -1, -1, "-", -1);
            note.setLine(line);
            note.setLineCol(offset);
            list.add(note);
        }
        int length = tab.get(keyList[0]).length();
        for (int i = 3; i < length; i++) {
            for (int j = 0; j < m; j++) {
                int line = (int)keyList[j];
                String tabLine = tab.get(line);
                int offset = tabLine.length()-tabLine.stripLeading().length();
                tabLine = tabLine.strip();
                Output note = null;
                if ((getCharFromString(tabLine, i) != '-') && (getCharFromString(tabLine, i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    note = new Output(
                            Character.toString(getCharFromString(tabLine, 0))
                                    + Character.toString(getCharFromString(tabLine, 1)),
                            -3, -3, Character.toString(getCharFromString(tabLine, i)), i);

                }
                if (note!=null) {
                    list.add(note);
                    note.setLine(line);
                    note.setLineCol(i+offset);
                }
                // Check if the element is "|" to add new tab element to the list
                if (getCharFromString(tabLine, i) == '|') {
                    note = new Output("*New Measure*", -1, -1, "-", i);
                    note.setLine(line);
                    note.setLineCol(i+offset);
                    list.add(note);
                    if (i != length - 1) {
                        i++;
                    } else {
                        break;
                    }
                }
            }
        }
        return list;
    }
}
