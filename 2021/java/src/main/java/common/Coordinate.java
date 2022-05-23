package common;

import java.util.Objects;

public class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate scaled(int magnitude) {
        return new Coordinate(x*magnitude, y*magnitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }
}
