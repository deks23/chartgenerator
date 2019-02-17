package pl.damiankotynia.model;

import java.io.Serializable;
import java.util.Objects;

public class MVector implements Serializable {
    private double x;
    private double y;

    public MVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public MVector add(MVector vectorA){
        double x = vectorA.getX() + this.x;
        double y = vectorA.getY() + this.y;
        return new MVector(x, y);
    }


    public MVector multiplyVector(double value){
        //TODO przepisać obliczenia na BigDecimal
        double x = this.x * value;
        double y = this.y * value;
        return new MVector(x, y);
    }

    public MVector multiplyVector(int value){
        //TODO przepisać obliczenia na BigDecimal
        double x = this.x * value;
        double y = this.y * value;
        return new MVector(x, y);
    }

    public MVector substractVector(MVector vector){
        double x = this.x - vector.getX();
        double y = this.y - vector.getY() ;
        return new MVector(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MVector mVector = (MVector) o;
        return Double.compare(mVector.x, x) == 0 &&
                Double.compare(mVector.y, y) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "MVector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
