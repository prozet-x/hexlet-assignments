package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> objects, int n) {
        return objects.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(n)
                .toList();
    }
}
// END
