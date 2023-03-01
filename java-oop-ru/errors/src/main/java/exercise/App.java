package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle c) {
        try {
            System.out.println(Math.round(c.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
