package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map getWordCount(String sentence) {
        Map<String, Integer> res = new HashMap<>();

        String[] words = sentence.split(" ");
        for(String word: words) {
            res.put(word, res.get(word));
        }
    }
}
//END
