package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String symbols, String word) {
        List<Character> symbolsList = new ArrayList<Character>();
        for (Character c: symbols.toCharArray()) {
            symbolsList.add(c);
        }
        for (Character c: word.toLowerCase().toCharArray()) {
            if (symbolsList.indexOf(c) < 0) {
                return false;
            }
            symbolsList.remove(c);
        }
        return true;
    }
}
//END
