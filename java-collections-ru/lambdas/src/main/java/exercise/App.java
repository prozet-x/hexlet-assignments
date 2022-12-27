package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return new String[][] {};
        }
        int rowsInRes = image.length * 2;
        int colsInRes = image[0].length * 2;
        String[] direct = Arrays.stream(image)
                .map(elem -> new String[][] {elem, elem})
                .flatMap(elem -> Arrays.stream(elem))
                .flatMap(elem -> Arrays.stream(elem))
                .map(elem -> new String[] {elem, elem})
                .flatMap(elem -> Arrays.stream(elem))
                .toArray(String[]::new);
        String[][] res = new String[rowsInRes][];
        for (int i = 1; i <= rowsInRes; i++) {
            res[i - 1] = Arrays.copyOfRange(direct, (i - 1) * colsInRes, i * colsInRes);
        }
        return res;
    }
}
// END
