package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> where) {
        List<Map<T, T>> res = new ArrayList<>();
        for (Map<T, T> book: books) {
            boolean good = true;
            for (Map.Entry<T, T> condition: where.entrySet()) {
                T key = condition.getKey();
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
