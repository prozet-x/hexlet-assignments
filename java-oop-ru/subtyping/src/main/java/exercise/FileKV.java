package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(String filePath, Map<String, String> initianMap) {
        this.filePath = filePath;
    }

    public void set(String key, String value) {
        Map<String, String> map = readMapFromFile();
        map.put(key, value);
        writeMapToFile(map);
    }

    public String get(String key, String defaultValue) {
        Map<String, String> map = readMapFromFile();
        return map.getOrDefault(key, defaultValue);
    }

    public Map<String, String> toMap() { return new HashMap<String, String>(readMapFromFile()); }

    public void unset(String key) {
        Map<String, String> map = readMapFromFile();
        map.remove(key);
        writeMapToFile(map);
    }

    private void writeMapToFile(Map<String, String> map) {
        Utils.writeFile(filePath, Utils.serialize(map));
    }

    private Map<String, String> readMapFromFile() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
