package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> objects, int n) {
        objects.sort((o1, o2) -> o1.compareTo(o2));
        return objects.stream().map(o -> o.toString()).limit(n).collect(Collectors.toList());
    }
}
// END
