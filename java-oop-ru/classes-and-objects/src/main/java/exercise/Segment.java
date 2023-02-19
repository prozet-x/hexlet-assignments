package exercise;

// BEGIN
public class Segment {
    private Point begin;
    private Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        final int midPointX = (getBeginPoint().getX() + getEndPoint().getX()) / 2;
        final int midPointY = (getBeginPoint().getY() + getEndPoint().getY()) / 2;
        return new Point(midPointX, midPointY);
    }
}
// END
