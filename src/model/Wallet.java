package model;

public class Wallet {
    private int userId;
    private double balance;

    public Wallet() {
    }

    public Wallet(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    // Nạp tiền vào ví
    public void deposit(double amount) {
        balance += amount;
    }

    // Rút tiền (trừ khi chơi hoặc thanh toán)
    public boolean withdraw(double amount) {
        if (amount > balance)
            return false;
        balance -= amount;
        return true;
    }
}