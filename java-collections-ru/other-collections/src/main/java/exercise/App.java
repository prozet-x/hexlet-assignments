package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> d1, Map<String, Object> d2) {
        Map<String, String> result = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>(d1.keySet());
        allKeys.addAll(d2.keySet());
        for (String key: allKeys) {
            String changes = "";
            if (d1.containsKey(key) && !d2.containsKey(key)) {
                changes = "deleted";
            } else if (!d1.containsKey(key) && d2.containsKey(key)) {
                changes = "added";
            } else {
                changes = d1.get(key).equals(d2.get(key)) ? "unchanged" : "changed";
            }
            result.put(key, changes);
        }
        return result;
    }
}
//END
