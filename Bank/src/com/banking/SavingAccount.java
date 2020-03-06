package com.banking;

public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount() {
    }

    public SavingAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }
}
