package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> res = new HashMap<>();
        if (sentence.equals("")) {
            return res;
        }
        String[] words = sentence.split(" ");
        for (String word: words) {
            Integer currentCount = res.get(word);
            res.put(word, currentCount == null ? 1 : currentCount.intValue() + 1);
        }
        return res;
    }

    public static String toString(Map<String, Integer> wordsMap) {
        if (wordsMap.size() == 0) {
            return "{}";
        }
        StringBuilder res = new StringBuilder();
        res.append("{");
        for (Map.Entry<String, Integer> word: wordsMap.entrySet()) {
            res.append("\n  ");
            res.append(word.getKey());
            res.append(": ");
            res.append(word.getValue());
        }
        res.append("\n}");
        return res.toString();
    }
}
//END
