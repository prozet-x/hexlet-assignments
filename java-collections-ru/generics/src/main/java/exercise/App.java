package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> res = new ArrayList<>();
        for(Map<String, String> book: books) {
            boolean good = true;
            for (Map.Entry<String, String> condition: where.entrySet()) {
                String key = condition.getKey();
                if (!(book.containsKey(key) && book.get(key).equals(condition.getValue()))) {
                    good = false;
                    break;
                }
            }
            if (good) {
                res.add(book);
            }
        }
        return res;
    }
}
//END
