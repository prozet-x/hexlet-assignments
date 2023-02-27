package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attrs) {
        super(name, attrs);
    }

    @Override
    public String toString() {
        return "<"
                + getName()
                + (super.getAttrsCount() > 0 ? " " : "")
                + attrsToString()
                + ">";
    }
}
// END
