package Parser;

import java.util.List;

import static Parser.textReader.getCharFromString;

public class DrumParser {

    /*
     * "ParsDrum" Takes the tab to parse and the list of the results so far. Then
     * makes changes and adds new elements to the list. returns the result list of
     * Output.
     */
    public static List<Output> ParseDrum(List<String> tab, List<Output> list, int m) {
        if (!list.isEmpty()) {
            list.add(new Output("# NEW TAB #", -1, -1, "-", -1));
        }
        int length = tab.get(0).length();
        for (int i = 3; i < length; i++) {
            for (int j = 0; j < m; j++) {
                if ((getCharFromString(tab.get(j), i) != '-') && (getCharFromString(tab.get(j), i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    list.add(new Output(
                            Character.toString(getCharFromString(tab.get(j), 0))
                                    + Character.toString(getCharFromString(tab.get(j), 1)),
                            -1, -1, Character.toString(getCharFromString(tab.get(j), i)), i));

                }
                // Check if the element is "|" to add new tab element to the list
                if (getCharFromString(tab.get(j), i) == '|') {
                    list.add(new Output("*New Measure*", -1, -1, "-", i));
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
