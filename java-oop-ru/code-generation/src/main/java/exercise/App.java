package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        try {
            Files.writeString(path, car.serialize());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Car extract(Path path) {
        try {
            String carSerialized = Files.readString(path);
            return Car.unserialize(carSerialized);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
// END
