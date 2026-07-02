package week_2.day_1;

public class BankAccount {
    // field storage
    private String owner;
    private double balance;

    // constructor
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // methods
    public void deposit(double amount) {
        if (amount <=0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public double getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}