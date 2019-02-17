package pl.damiankotynia.model;

import java.io.Serializable;

public class Point implements Serializable {

    protected MVector position;

    public Point(MVector position) {
        this.position = position;
    }

    public MVector getPosition() {
        return position;
    }

    public void setPosition(MVector position) {
        this.position = position;
    }
}
