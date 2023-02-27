package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balcony;
    private int floor;

    public Flat(double area, double balcony, int floor) {
        this.area = area;
        this.balcony = balcony;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }

    @Override
    public double getArea() {
        return balcony + area;
    }

    @Override
    public int compareTo(Home another) {
        double thisArea = getArea();
        double anotherArea = another.getArea();
        if (thisArea == anotherArea) {
            return 0;
        }
        return thisArea > anotherArea ? 1 : -1;
    }
}
// END
