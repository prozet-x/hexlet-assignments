package exercise;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage db) {
        Set<String> keys = db.toMap().keySet().stream().collect(Collectors.toSet());
        for (String key : keys) {
            String value = db.get(key, "default");
            db.unset(key);
            db.set(value, key);
        }
    }
}
// END
