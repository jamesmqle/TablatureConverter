package Parser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static Parser.textReader.getCharFromString;

public class GuitarParser {


    // reads to end of file
    /*
     * "ParsGuitar" Takes the tab to parse and the list of the results so far. Then
     * makes changes and adds new elements to the list. returns the result list of
     * Output.
     */
    public static List<Output> ParseGuitar(LinkedHashMap<Integer, String> tab, List<Output> list, int m) {
        // Check if the list is empty to add new tab element to the list
        Object[] keyList = tab.keySet().toArray();
        if (!list.isEmpty()) {
            int line = (int)keyList[0];
            String tabLine = tab.get(line);
            int offset = tabLine.length()-tabLine.stripLeading().length();
            Output note = new Output("# NEW TAB #", -2, -2, "-", -2);
            note.setLine(line);
            note.setLineCol(offset);
            list.add(note);
        }
        int length = tab.get(keyList[0]).length();
        for (int i = 2; i < length; i++) {
            for (int j = 0; j < m; j++) {
                //TODO m might be a larger number than the num of items in keyList. check the logic for what determines m to make sure it is never.
                int line = (int)keyList[j];
                String tabLine = tab.get(line);
                int offset = tabLine.length()-tabLine.stripLeading().length();
                tabLine = tabLine.strip();
                if ((getCharFromString(tabLine, i) != '-') && (getCharFromString(tabLine, i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    Output note = null;
                    if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|'))
                            && ((getCharFromString(tabLine, i + 1) == '-')
                            || (getCharFromString(tabLine, i + 1) == '|'))) {
                        note = new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i))), -1, "-", i);

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
                    // 3 digits with technique
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|')
                            || (getCharFromString(tabLine, i - 1) == '/')
                            || (getCharFromString(tabLine, i - 1) == 's')
                            || (getCharFromString(tabLine, i - 1) == 'p')
                            || (getCharFromString(tabLine, i - 1) == 'h')
                            || (getCharFromString(tabLine, i - 1) == 'g'))
                            && ((getCharFromString(tabLine, i + 1) == '/')
                            || (getCharFromString(tabLine, i + 1) == 's')
                            || (getCharFromString(tabLine, i + 1) == 'p')
                            || (getCharFromString(tabLine, i + 1) == 'h')
                            || (getCharFromString(tabLine, i - 1) == 'g')  // ADDED FOR GRACE NOTES
                            || (( getCharFromString(tabLine, i - 1) == '[' // ADDED THIS FOR HARMONICS
                            && getCharFromString(tabLine, i + 1) == ']'))) // ADDED THIS FOR HARMONICS
                            && ((getCharFromString(tabLine, i + 2) != '-')
                            || (getCharFromString(tabLine, i + 2) != '|')) //7h3p5
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
                        note.setLineCol(i+offset);
                    }

                    // 3 digits number harmonic like [2]
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if ((getCharFromString(tabLine, i - 1) == '-' || getCharFromString(tabLine, i - 1) == '|')
                            && (getCharFromString(tabLine, i) == '[')
                            && (getCharFromString(tabLine, i + 1) != '/')
                            && (getCharFromString(tabLine, i + 1) != 's')
                            && (getCharFromString(tabLine, i + 2) == ']')
                            && (getCharFromString(tabLine, i + 3) == '-' || getCharFromString(tabLine, i + 3) == '|')) {
                        list.add(new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 1))),
                                -1, "[]", i));
                    }

                    // 4 digits grace like g0h1
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if (((getCharFromString(tabLine, i - 1) == '-')
                            || (getCharFromString(tabLine, i - 1) == '|'))
                            && (getCharFromString(tabLine, i) != '-')
                            && (getCharFromString(tabLine, i) != '|')
                            && ((getCharFromString(tabLine, i + 1) != '-')
                            && (getCharFromString(tabLine, i + 1) != '|')
                            && (getCharFromString(tabLine, i + 2) != '-')
                            && (getCharFromString(tabLine, i + 2) != '|'))
                            && (getCharFromString(tabLine, i + 3) != '-')
                            && (getCharFromString(tabLine, i + 3) != '|')
                            && ((getCharFromString(tabLine, i + 4) == '-')
                            || (getCharFromString(tabLine, i + 4) == '|'))) {
                        list.add(new Output(Character.toString(getCharFromString(tabLine, 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 1))),
                                Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 3))),
                                Character.toString(getCharFromString(tabLine, i + 2)), i,Character.toString(getCharFromString(tabLine, i))));
                    }
                    // 3 digits number harmonic like [2]
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if ((getCharFromString(tab.get(j), i - 1) == '-' || getCharFromString(tab.get(j), i - 1) == '|')
                            && (getCharFromString(tab.get(j), i) == '[')
                            && (getCharFromString(tab.get(j), i + 1) != '/')
                            && (getCharFromString(tab.get(j), i + 1) != 's')
                            && (getCharFromString(tab.get(j), i + 2) == ']')
                            && (getCharFromString(tab.get(j), i + 3) == '-' || getCharFromString(tab.get(j), i + 3) == '|')) {
                        list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 1))),
                                -1, "[]", i));
                    }
                    // 4 digits grace like g0h1
                    // Check if the note is proper 4 digit to add new tab element to the list
                    else if (((getCharFromString(tab.get(j), i - 1) == '-')
                            || (getCharFromString(tab.get(j), i - 1) == '|'))
                            && (getCharFromString(tab.get(j), i) != '-')
                            && (getCharFromString(tab.get(j), i) != '|')
                            && ((getCharFromString(tab.get(j), i + 1) != '-')
                            && (getCharFromString(tab.get(j), i + 1) != '|')
                            && (getCharFromString(tab.get(j), i + 2) != '-')
                            && (getCharFromString(tab.get(j), i + 2) != '|'))
                            && (getCharFromString(tab.get(j), i + 3) != '-')
                            && (getCharFromString(tab.get(j), i + 3) != '|')
                            && ((getCharFromString(tab.get(j), i + 4) == '-')
                            || (getCharFromString(tab.get(j), i + 4) == '|'))) {
                        list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 1))),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 3))),
                                Character.toString(getCharFromString(tab.get(j), i + 2)), i,Character.toString(getCharFromString(tab.get(j), i))));
                    }
                    // 5 digits Triple Guitar Technique like 0h2p0
                    // Check if the note is proper 5 digit to add new tab element to the list
                    else if (((getCharFromString(tab.get(j), i - 1) == '-')
                            || (getCharFromString(tab.get(j), i - 1) == '|'))
                            && (getCharFromString(tab.get(j), i) != '-')
                            && (getCharFromString(tab.get(j), i) != '|')
                            && (getCharFromString(tab.get(j), i + 1) != '-')
                            && (getCharFromString(tab.get(j), i + 1) != '|')
                            && (getCharFromString(tab.get(j), i + 2) != '-')
                            && (getCharFromString(tab.get(j), i + 2) != '|')
                            && (getCharFromString(tab.get(j), i + 3) != '-')
                            && (getCharFromString(tab.get(j), i + 3) != '|')
                            && (getCharFromString(tab.get(j), i + 4) != '-')
                            && (getCharFromString(tab.get(j), i + 4) != '|')
                            && ((getCharFromString(tab.get(j), i + 4) == '-')
                            || (getCharFromString(tab.get(j), i + 5) == '|'))) {
                        list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i ))),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 2))),
                                Character.toString(getCharFromString(tab.get(j), i + 1)), i,Character.toString(getCharFromString(tab.get(j), i + 3)),Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 4)))));
                    }
                }

                // 5 digits Triple Guitar Technique like 0h2p0
                // Check if the note is proper 5 digit to add new tab element to the list
                else if (((getCharFromString(tabLine, i - 1) == '-')
                        || (getCharFromString(tabLine, i - 1) == '|'))
                        && (getCharFromString(tabLine, i) != '-')
                        && (getCharFromString(tabLine, i) != '|')
                        && (getCharFromString(tabLine, i + 1) != '-')
                        && (getCharFromString(tabLine, i + 1) != '|')
                        && (getCharFromString(tabLine, i + 2) != '-')
                        && (getCharFromString(tabLine, i + 2) != '|')
                        && (getCharFromString(tabLine, i + 3) != '-')
                        && (getCharFromString(tabLine, i + 3) != '|')
                        && (getCharFromString(tabLine, i + 4) != '-')
                        && (getCharFromString(tabLine, i + 4) != '|')
                        && ((getCharFromString(tabLine, i + 4) == '-')
                        || (getCharFromString(tabLine, i + 5) == '|'))) {
                    list.add(new Output(Character.toString(getCharFromString(tabLine, 0)),
                            Integer.parseInt(Character.toString(getCharFromString(tabLine, i ))),
                            Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 2))),
                            Character.toString(getCharFromString(tabLine, i + 1)), i,Character.toString(getCharFromString(tabLine, i + 3)),Integer.parseInt(Character.toString(getCharFromString(tabLine, i + 4)))));
                }

                // Check if the element is "|" to add new tab element to the list
                if (getCharFromString(tabLine, i) == '|') {
                    Output note = new Output("*New Measure*", -1, -1, "-", i);
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
