package com.company;

public abstract class Fish extends Animal implements WaterLiveable {

    public Fish (String name) {
        super(name);
    }

    @Override
    public boolean isWarmBlooded() {
        return false;
    }

    @Override
    public boolean canLiveOnLand() {
        return false;
    }
}
