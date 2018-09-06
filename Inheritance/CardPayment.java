package com.company;
import java.time.LocalDate;

public class CardPayment extends Payment {
    private String name;
    private int number;

    public void setName(String name) {
        if (name == null || name.equals("")) {
            System.out.println("Please enter a name.");
        }
        else {
            this.name = name;
        }
    }

    public String getName () {
        return name;
    }

    public void setNumber(int number) {
        if (number >= 0) {
            this.number = number;
        }
        else {
            System.out.println("Number cannot be less than zero.");
        }
    }

    public int getNumber () {
        return number;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof CardPayment) {
            CardPayment cardPayment1 = (CardPayment) obj;

            return this.getMoneyAmount() == cardPayment1.getMoneyAmount() && this.getDate().equals(cardPayment1.getDate())
                    && this.getName().equals(cardPayment1.getName()) && this.getNumber() == cardPayment1.getNumber();
        }
        return false;
    }

    @Override
    public String toString () {
        return "The name is " + getName() + " and the credit card number is " + getNumber() + "." + System.lineSeparator()
                + super.toString();
    }

    /*
    to check my code
     */
    public static void main (String [] args) {
        CashPayment cashPayment1 = new CashPayment();
        cashPayment1.setMoneyAmount(20.0);
        cashPayment1.setDate(LocalDate.now());
        System.out.println(cashPayment1);

        CardPayment cardPayment1 = new CardPayment();
        cardPayment1.setName("shreya");
        cardPayment1.setNumber(123);
        cardPayment1.setMoneyAmount(30);
        cardPayment1.setDate(LocalDate.now());

        CardPayment cardPayment2 = new CardPayment();
        cardPayment2.setName("shreya");
        cardPayment2.setNumber(123);
        cardPayment2.setMoneyAmount(30);
        cardPayment2.setDate(LocalDate.now());
        System.out.println(cardPayment1);
        System.out.println(cardPayment2);
        System.out.println(cardPayment2.equals(cardPayment1));



    }
}
