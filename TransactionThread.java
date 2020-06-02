package com.company;

import java.util.Random;

public class TransactionThread extends Thread {

    private BankAccount from;
    private BankAccount to;
    private Bank bank;


    TransactionThread(BankAccount from, BankAccount to, Bank bank) {
        this.from = from;
        this.to = to;
        this.bank = bank;
    }

    @Override
    public void run() {
        double amount = from.getMoney() * new Random().nextDouble();
        Bank.moneyTransfer(from, to, amount);
    }
}
