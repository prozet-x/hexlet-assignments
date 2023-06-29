package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        MinThread minThread = new MinThread(arr);
        MaxThread maxThread = new MaxThread(arr);

        minThread.start();
        LOGGER.log(Level.INFO, "minThread started");
        maxThread.start();
        LOGGER.log(Level.INFO, "maxThread started");

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Map.of("min", minThread.getMin(), "max", maxThread.getMax());
    }
    // END
}
