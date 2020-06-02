package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Before transactions : "+Bank.calculateAllMoney());
        System.out.println("zero : "+Bank.getAllBankAccounts().get(0).getMoney());
        System.out.println("first : "+Bank.getAllBankAccounts().get(1).getMoney());
        System.out.println("second : "+ Bank.getAllBankAccounts().get(2).getMoney());
        System.out.println("third : "+Bank.getAllBankAccounts().get(3).getMoney());
        System.out.println("Enter a number of transactions : ");
        int countOfTransaction = new Scanner(System.in).nextInt();
        Bank bank = new Bank();
        for (int itter = 0; itter < countOfTransaction; itter++) {
            BankAccount from = Bank.getAllBankAccounts().get((new Random().nextInt((Bank.getAllBankAccounts().size()))));
            BankAccount to = Bank.getAllBankAccounts().get((new Random().nextInt((Bank.getAllBankAccounts().size()))));
            new TransactionThread(from,to,bank).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After transactions : "+Bank.calculateAllMoney());
        System.out.println("zero : "+Bank.getAllBankAccounts().get(0).getMoney());
        System.out.println("first : "+Bank.getAllBankAccounts().get(1).getMoney());
        System.out.println("second : "+ Bank.getAllBankAccounts().get(2).getMoney());
        System.out.println("third : "+Bank.getAllBankAccounts().get(3).getMoney());

    }
}
