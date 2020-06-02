package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

class Bank {

    private static final Object lock = new Object();
    private static volatile List<BankAccount> allBankAccounts = generate(4);

    static void moneyTransfer(BankAccount from, BankAccount to, Double amount) {
        long fromId = from.getId();
        long toId = to.getId();
        if (fromId < toId) {
            synchronized (from) {
                synchronized (to) {
                    executeMoneyTransfer(from, to, amount);
                }
            }
        } else if (fromId > toId) {
            synchronized (to) {
                synchronized (from) {
                    executeMoneyTransfer(from, to, amount);
                }
            }
        } else {
            synchronized (lock) {
                synchronized (from) {
                    executeMoneyTransfer(from, to, amount);
                }
            }
        }
    }

    private static void executeMoneyTransfer(BankAccount from, BankAccount to, Double amount) {
        BankAccount newAccFrom = from.getMoneyFromAccount(amount);
        BankAccount newAccTo = to.payMoney(amount);
        allBankAccounts.set((int) newAccFrom.getId(), newAccFrom);
        allBankAccounts.set((int) newAccTo.getId(), newAccTo);
    }

    static List<BankAccount> getAllBankAccounts() {
        return allBankAccounts;
    }

    static Double calculateAllMoney() {
        DoubleStream allMoney = getAllBankAccounts().stream().mapToDouble(x -> x.getMoney());
        return allMoney.reduce(Double::sum).orElse(Double.NaN);
    }

    private static List<BankAccount> generate(int size) {
        List<BankAccount> list = new ArrayList<>();
        IntStream.range(0, size).forEach(accountId -> list.
                add(new BankAccount((0.0 + (100.0 - 0.0) * new Random().nextDouble()), accountId)));
        return list;
    }
}
