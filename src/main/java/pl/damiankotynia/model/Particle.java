package pl.damiankotynia.model;


import java.io.Serializable;

public class Particle extends Point implements Serializable {
    private MVector velocity;
    private MVector bestLocalPosition;
    private Double currentValue;
    private Double bestValue;


    public Particle(MVector position, MVector velocity, OptimizationTarget optimizationTarget){
        super(position);
        this.velocity = velocity;
        if(optimizationTarget.equals(OptimizationTarget.MIN)){
            this.currentValue = Double.MAX_VALUE;
            this.bestValue = Double.MAX_VALUE;
        }else {
            this.currentValue = Double.MIN_VALUE;
            this.bestValue = Double.MIN_VALUE;
        }
    }


    public MVector getVelocity() {
        return velocity;
    }

    public void setVelocity(MVector velocity) {
        this.velocity = velocity;
    }

    public MVector getBestLocalPosition() {
        return bestLocalPosition;
    }

    public void setBestLocalPosition(MVector bestLocalPosition) {
        this.bestLocalPosition = bestLocalPosition;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getBestValue() {
        return bestValue;
    }

    public void setBestValue(Double bestValue) {
        this.bestValue = bestValue;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", currentValue=" + currentValue +


                ", bestValue=" + bestValue +
                '}';
    }
}
