package exercise;

import java.util.PropertyResourceBundle;

// BEGIN
public class Circle {
    private Point center;
    private Integer radius;

    public Circle(Point center, Integer radius) {
        this.center = center;
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius is negative!");
        }

        return Math.PI * radius * radius;
    }
}
// END
