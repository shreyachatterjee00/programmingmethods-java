package com.company;

public abstract class Amphibian extends Animal implements WaterLiveable{

    public Amphibian (String name) {
        super(name);
    }

    @Override
    public boolean isWarmBlooded () {
        return false;
    }

    @Override
    public boolean canLiveOnLand () {
        return true;
    }

}
