package online.polp;

import online.polp.singletons.RandomSingleton;
import org.decimal4j.mutable.MutableDecimal2f;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Force initialization of the singleton
        RandomSingleton.getInstance();

        BankAccount bankAccount = new BankAccount(new MutableDecimal2f(100));

        Thread withdrawThread = new Thread(new WithdrawThread(bankAccount));
        Thread depositThread = new Thread(new DepositThread(bankAccount));

        withdrawThread.start();
        Thread.sleep(100);
        depositThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}