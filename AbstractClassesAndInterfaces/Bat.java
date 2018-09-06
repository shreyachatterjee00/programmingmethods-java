package com.company;

public class Bat extends Mammal implements Flyable {

private double flightSpeed;
    public Bat (String name, double flightSpeed) {
        super(name);
        this.flightSpeed = flightSpeed;
    }

    @Override
    public double getFlightSpeed() {
        return flightSpeed;
    }
}
