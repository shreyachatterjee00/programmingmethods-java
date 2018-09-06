package com.company;
import java.time.LocalDate;

public class Payment {
    private double moneyAmount;
    private LocalDate date;

    public void setMoneyAmount (double moneyAmount) {
        if (moneyAmount >= 0) {
            this.moneyAmount = moneyAmount;
        }
        else {
            System.out.println("Money amount cannot be less than zero.");
        }
    }

    public double getMoneyAmount () {
        return moneyAmount;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate () {
        return date;
    }

   @Override
   public String toString () {
       return "The amount paid is " + getMoneyAmount() + " dollars and the date of payment is " + getDate() + ".";
    }


}
