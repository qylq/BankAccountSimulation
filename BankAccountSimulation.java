package inter.qylex.m3.BankAccountSimulation;

import java.util.Random;

public class BankAccountSimulation {
    private static int account = 1000;
    private static Object KEY = new Object();
    private static final Random random = new Random();

    public static void main(String[] args) {
        Thread deposit = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(random.nextInt(1500) + 500);
                    int amount = random.nextInt(400) + 100;
                    synchronized (KEY) {
                        account += amount;
                        System.out.println("Пополнение на сумму: " + amount + ". Новый баланс: " + account);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Thread withdraw = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(random.nextInt(1500) + 500);
                    int amount = random.nextInt(700) + 100;
                    synchronized (KEY) {
                        if (amount <= account) {
                            account -= amount;
                            System.out.println("Снятие на сумму: " + amount + ". Новый баланс: " + account);
                        } else {
                            System.out.println("Не хватает!");
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        new Thread(deposit).start();
        try {
            deposit.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        new Thread(withdraw).start();
    }
}
