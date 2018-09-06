package com.company;

public class AnimalKingdomTester {
    public static void main(String[] args) {
        Animal[] animals = new Animal[5];
        animals[0] = new Cat("Mr. Meowerson");
        animals[1] = new BlueWhale("Blubber");
        animals[2] = new Bat("Dracky", 15);
        animals[3] = new Goldfish("Nemo");
        animals[4] = new Frog("Prince");


        for (Animal a : animals) {
            System.out.println(a + " is a " + a.getClass().getSimpleName() + " which " + (a.isWarmBlooded() ? "is" : "is not") + " warm blooded.");
            if (a instanceof Adoptable) System.out.println("\t" + ((Adoptable) a).getHomeCareInstructions());
            if (a instanceof Flyable) System.out.println("\tFlies up to " + ((Flyable) a).getFlightSpeed() + " mph!");
            if (a instanceof WaterLiveable)
                System.out.println("\t" + a.getName() + " can live in the water " + (((WaterLiveable) a).canLiveOnLand() ? " and can also" : "but cannot") + " live on land.");
        }
    }
}
