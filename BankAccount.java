package com.company;

public class BankAccount {
    private long id;
    private Double money;
    private int countOfTransaction;

    BankAccount(Double money, int id) {
        this.money = money;
        this.id = id;
    }

    BankAccount getMoneyFromAccount(Double amount) {
        if (this.getMoney() - amount >= 0) {
            this.setMoney(this.getMoney()-amount);
        } else {
            throw new Error("Not enough money for payment");
        }
        countOfTransaction++;
        return this;
    }

    BankAccount payMoney(Double amount) {
        this.setMoney(this.getMoney() + amount);
        countOfTransaction++;
        return this;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public int getCountOfTransaction() {
        return countOfTransaction;
    }

    public void setCountOfTransaction(int countOfTransaction) {
        this.countOfTransaction = countOfTransaction;
    }
}
