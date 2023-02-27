package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attrs, String body, List<Tag> children) {
        super(name, attrs);
        this.body = body;
        this.children = children.stream().collect(Collectors.toList());
    }

    private String childrenAsString() {
        return children.stream().map(c -> c.toString()).collect(Collectors.joining());
    }

    private String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "<"
                + getName()
                + (super.getAttrsCount() > 0 ? " " : "")
                + attrsToString()
                + ">"
                + getBody()
                + childrenAsString()
                + "</"
                + getName()
                + ">";
    }
}
// END
