package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        return Arrays.asList(content.split("\n")).stream()
                .filter(string -> string.startsWith("environment"))
                .map(string -> string.replaceAll("environment=", "").replace("\"", ""))
                .flatMap(string -> Stream.of(string.split(",")))
                .filter(string -> string.startsWith("X_FORWARDED_"))
                .map(string -> string.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
