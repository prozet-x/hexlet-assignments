package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> map;

    public InMemoryKV(Map<String, String> map) {
        this.map = new HashMap<String, String>(map);
    }

    public void set(String key, String value) {
        map.put(key, value);
    }

    public void unset(String key) {
        map.remove(key);
    }

    public String get(String key, String defaultValue) {
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    public Map<String, String> toMap() {
        return new HashMap<String, String>(map);
    }
}
// END
