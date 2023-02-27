package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String name;
    private Map<String, String> attrs;

    public Tag(String name, Map<String, String> attrs) {
        this.name = name;
        //Map <String, String> attrsCloned = attrs.entrySet().stream().collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        this.attrs = attrs;
    }

    protected String attrsToString() {
        return attrs.entrySet(). stream()
                .map(p -> p.getKey() + "=\"" + p.getValue() + "\"")
                .collect(Collectors.joining(" "));
    }

    protected final String getName() {
        return name;
    }

    public Integer getAttrsCount() {
        return attrs.size();
    }
    public abstract String toString();
}
// END
