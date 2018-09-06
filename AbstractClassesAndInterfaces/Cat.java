package com.company;

public class Cat extends Mammal implements Adoptable {

    public Cat (String name) {
        super(name);
    }

    @Override
    public String getHomeCareInstructions() {
        return "To care for " + getName() + ", you must get a litter box, toys, a bed, and food.";
    }

}
