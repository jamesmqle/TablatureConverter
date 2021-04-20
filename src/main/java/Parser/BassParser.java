package Parser;

import java.util.List;

import static Parser.textReader.getCharFromString;
import static Parser.textReader.isInteger;

public class BassParser {

    /*
     * "ParsBass" Takes the tab to parse and the list of the results so far. Then
     * makes changes and adds new elements to the list. returns the result list of
     * Output.
     */

    public static List<Output> ParseBass(List<String> tab, List<Output> list, int m) {
        // Check if the list is empty to add new tab element to the list
        if (!list.isEmpty()) {
            list.add(new Output("# NEW TAB #", -2, -2, "-", -2));
        }
        int length = tab.get(0).length();
        for (int i = 2; i < length; i++) {
            for (int j = 0; j < m; j++) {
                if ((getCharFromString(tab.get(j), i) != '-') && (getCharFromString(tab.get(j), i) != '|')) {
                    // 1 digit
                    // Check if the note is proper 1 digit to add new tab element to the list
                    if (((getCharFromString(tab.get(j), i - 1) == '-')
                            || (getCharFromString(tab.get(j), i - 1) == '|'))
                            && ((getCharFromString(tab.get(j), i + 1) == '-')
                            || (getCharFromString(tab.get(j), i + 1) == '|'))) {
                        if (isInteger(Character.toString(getCharFromString(tab.get(j), i)))) {
                            list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                    Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i))), -1, "-",
                                    i));
                        } else {
                            list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)), -1, -1,
                                    Character.toString(getCharFromString(tab.get(j), i)), i));
                        }

                    }
                    // 2 digits
                    // Check if the note is proper 2 digit to add new tab element to the list
                    else if (((getCharFromString(tab.get(j), i - 1) == '-')
                            || (getCharFromString(tab.get(j), i - 1) == '|'))
                            && ((getCharFromString(tab.get(j), i + 2) == '-')
                            || (getCharFromString(tab.get(j), i + 2) == '|'))
                            && ((getCharFromString(tab.get(j), i + 1) != '-')
                            || (getCharFromString(tab.get(j), i + 1) != '|'))) {
                        list.add(
                                new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                        Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i))
                                                + Character.toString(getCharFromString(tab.get(j), i + 1))),
                                        -1, "-", i));

                    }
                    // 3 digits number harmonic like (2)
                    // Check if the note is proper 3 digit to add new tab element to the list
                    else if ((getCharFromString(tab.get(j), i - 1) == '-' || getCharFromString(tab.get(j), i - 1) == '|')
                            && (getCharFromString(tab.get(j), i) == '(')
                            && (getCharFromString(tab.get(j), i + 1) != '/')
                            && (getCharFromString(tab.get(j), i + 1) != 's')
                            && (getCharFromString(tab.get(j), i + 2) == ')')
                            && (getCharFromString(tab.get(j), i + 3) == '-' || getCharFromString(tab.get(j), i + 3) == '|')) {
                        list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 1))),
                                -1, "()", i));
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

                    // 3 digit with technique
                    else if (((getCharFromString(tab.get(j), i - 1) == '-')
                            || (getCharFromString(tab.get(j), i - 1) == '|'))
                            && ((getCharFromString(tab.get(j), i + 1) == '/')
                            || (getCharFromString(tab.get(j), i + 1) == 's')
                            || (getCharFromString(tab.get(j), i + 1) == 'p')
                            || (getCharFromString(tab.get(j), i + 1) == 'h'))
                            && ((getCharFromString(tab.get(j), i + 2) != '-')
                            || (getCharFromString(tab.get(j), i + 2) != '|'))
                            && ((getCharFromString(tab.get(j), i + 3) == '-')
                            || (getCharFromString(tab.get(j), i + 3) == '|'))) {
                        list.add(new Output(Character.toString(getCharFromString(tab.get(j), 0)),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i))),
                                Integer.parseInt(Character.toString(getCharFromString(tab.get(j), i + 2))),
                                Character.toString(getCharFromString(tab.get(j), i + 1)), i));
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
