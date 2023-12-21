package inter.qylex.m3.BankAccountSimulation;

public class BankAccount {
    private int balance;
    public boolean flag = false;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        synchronized (KEY) {
            balance += amount;
            System.out.println("Пополнение на сумму: " + amount + ". Новый баланс: " + balance);
            KEY.notify();
            try {
                KEY.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void withdraw(int amount) {
        synchronized (KEY) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Снятие на сумму: " + amount + ". Новый баланс: " + balance);
                KEY.notify();
                try {
                    KEY.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Не хватает!");
                flag = true;
            }
        }
    }
    public static final Object KEY = new Object();
}
