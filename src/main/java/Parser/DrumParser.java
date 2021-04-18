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
        if (!list.isEmpty()) {
            list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
        }
        Object[] keyList = tab.keySet().toArray();
        int length = tab.get(keyList[0]).length();
        for (int i = 3; i < length; i++) {
            for (int j = 0; j < m; j++) {
                int line = (int)keyList[j];
                String tabLine = tab.get(line);
                if ((getCharFromString(tabLine, i) != '-') && (getCharFromString(tabLine, i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    list.add(new Output(
                            Character.toString(getCharFromString(tabLine, 0))
                                    + Character.toString(getCharFromString(tabLine, 1)),
                            -1, -1, Character.toString(getCharFromString(tabLine, i)), i));

                }
                // Check if the element is "|" to add new tab element to the list
                if (getCharFromString(tabLine, i) == '|') {
                    Output note = new Output("*New Measure*", -1, -1, "-", i);
                    note.setLine(line);
                    note.setLineCol(i);
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
