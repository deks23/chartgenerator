package pl.damiankotynia.model;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
