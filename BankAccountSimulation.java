package inter.qylex.m3.BankAccountSimulation;

import java.util.Random;

public class BankAccountSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(999);

        Thread depositThread = new Thread(() -> {
            Random random = new Random();
            while (!account.flag) {
                int depositAmount = random.nextInt(333);
                account.deposit(depositAmount);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Thread withdrawThread = new Thread(() -> {
            Random random = new Random();
            while (!account.flag) {
                int withdrawAmount = random.nextInt(333);
                account.withdraw(withdrawAmount);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        depositThread.start();
        withdrawThread.start();
    }
}
