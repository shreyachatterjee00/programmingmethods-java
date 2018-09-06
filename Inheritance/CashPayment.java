package com.company;
import java.time.LocalDate;

public class CashPayment extends Payment {

    @Override
    public String toString () {
        return super.toString();
    }

    public static void main (String [] args) {
        CashPayment cashPayment1 = new CashPayment();
        cashPayment1.setMoneyAmount(20.0);
        cashPayment1.setDate(LocalDate.now());
        System.out.println(cashPayment1);
    }

}
