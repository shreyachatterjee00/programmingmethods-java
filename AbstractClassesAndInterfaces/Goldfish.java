package com.company;

public class Goldfish extends Fish implements Adoptable {

    public Goldfish (String name) {
        super(name);
    }

    public String getHomeCareInstructions () {
        return "To care for " + getName() + ", feed your fish daily and clean its tank at least once every week.";
    }
}
