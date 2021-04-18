package Parser;

import java.util.LinkedHashMap;
import java.util.List;

import static Parser.textReader.getCharFromString;
import static Parser.textReader.isInteger;

public class BassParser {

    /*
     * "ParsBass" Takes the tab to parse and the list of the results so far. Then
     * makes changes and adds new elements to the list. returns the result list of
     * Output.
     */

    public static List<Output> ParseBass(LinkedHashMap<Integer, String> tab, List<Output> list, int m) {
        // Check if the list is empty to add new tab element to the list
        if (!list.isEmpty()) {
            list.add(new Output("# NEW TAB #", -2, -2, "-", -2));
        }
        Object[] keyList = tab.keySet().toArray();
        int length = tab.get(keyList[0]).length();
        for (int i = 2; i < length; i++) {
            for (int j = 0; j < m; j++) {
                int line = (int)keyList[j];
                String tabLine = tab.get(line);
                if ((getCharFromString(tabLine, i) != '-') && (getCharFromString(tabLine, i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    Output note = null;
                    if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|'))
                            && ((getCharFromString(tabLine, i + 1) == '-')
                            || (getCharFromString(tabLine, i + 1) == '|'))) {
                        if (isInteger(Character.toString(getCharFromString(tabLine, i)))) {
                            note = new Output(Character.toString(getCharFromString(tabLine, 0)),
                                    Integer.parseInt(Character.toString(getCharFromString(tabLine, i))), -1, "-",
                                    i);
                        } else {
                            note = new Output(Character.toString(getCharFromString(tabLine, 0)), -1, -1,
                                    Character.toString(getCharFromString(tabLine, i)), i);
                        }

                    }
                    // 2 digits
                    // Check if the note is proper 2 digit to add new tab element to the list
                    else if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|'))
                            && ((getCharFromString(tabLine, i + 2) == '-')
                            || (getCharFromString(tabLine, i + 2) == '|'))
                            && ((getCharFromString(tabLine, i + 1) != '-')
                            || (getCharFromString(tabLine, i + 1) != '|'))) {
                        note = new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i))
                                        + Character.toString(getCharFromString(tabLine, i + 1))),
                                -1, "-", i);

                    }
                    // 3 digits number in parentheses like (0)
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if ((getCharFromString(tabLine, i - 1) == '-')
                            && (getCharFromString(tabLine, i + 1) != '-')
                            && (getCharFromString(tabLine, i + 1) != '/')
                            && (getCharFromString(tabLine, i + 1) != 's')
                            && (getCharFromString(tabLine, i + 2) != '-')
                            && (getCharFromString(tabLine, i + 3) == '-')) {
                        note = new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i))
                                        + Character.toString(getCharFromString(tabLine, i + 1))
                                        + Character.toString(getCharFromString(tabLine, i + 2))),
                                -1, "-", i);
                    }

                    // 3 digit with technique
                    else if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|'))
                            && ((getCharFromString(tabLine, i + 1) == '/')
                            || (getCharFromString(tabLine, i + 1) == 's')
                            || (getCharFromString(tabLine, i + 1) == 'p')
                            || (getCharFromString(tabLine, i + 1) == 'h'))
                            && ((getCharFromString(tabLine, i + 2) != '-')
                            || (getCharFromString(tabLine, i + 2) != '|'))
                            && ((getCharFromString(tabLine, i + 3) == '-')
                            || (getCharFromString(tabLine, i + 3) == '|'))) {
                        note = new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i))),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 2))),
                                Character.toString(getCharFromString(tabLine, i + 1)), i);
                    }
                    if (note!=null) {
                        list.add(note);
                        note.setLine(line);
                        note.setLineCol(i);
                    }
                }
                // Check if the element is "|" to add new tab element to the list
                if (getCharFromString(tabLine, i) == '|') {
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
