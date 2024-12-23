/* public class Wallet {
    private double balance;

    public Wallet() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        balance += amount;
        System.out.println("Wallet topped up: " + amount);
    }

    public void deduct(double amount) {
        balance -= amount;
        System.out.println("Amount deducted: " + amount);
    }
} */

// idk which one better so you check new one below and comapre with above

public class Wallet
{
    private double balance;

    public Wallet()
    {
        this.balance = 0.0;
    }

    public void topUp(double amount)
    {
        balance += amount;
    }

    public boolean deduct(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance()
    {
        return balance;
    }
}

