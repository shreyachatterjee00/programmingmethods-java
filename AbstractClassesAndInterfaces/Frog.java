package com.company;

public class Frog extends Amphibian implements Adoptable {

    public Frog (String name) {
        super(name);
    }

    @Override
    public String getHomeCareInstructions() {
        return "To care for " + getName() + ", buy an aquarium that is part water and part land. Make sure it is well ventilated.";
    }
}
