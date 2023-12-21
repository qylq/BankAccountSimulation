package inter.qylex.m3.BankAccountSimulation;

public class BankAccount {
    private int balance;
    public boolean flag = false;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Пополнение на сумму: " + amount + ". Новый баланс: " + balance);
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Снятие на сумму: " + amount + ". Новый баланс: " + balance);
        } else {
            System.out.println("Не хватает!");
            flag = true;
        }
    }
    public static final Object KEY = new Object();
}
