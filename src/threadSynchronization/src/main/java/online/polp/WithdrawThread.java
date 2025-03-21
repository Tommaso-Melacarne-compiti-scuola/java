package online.polp;

import lombok.RequiredArgsConstructor;
import online.polp.colorize.Color;
import online.polp.singletons.RandomSingleton;
import org.decimal4j.immutable.Decimal2f;

import java.util.Random;

@RequiredArgsConstructor
public class WithdrawThread implements Runnable {
    private final BankAccount bankAccount;

    @Override
    public void run() {
        Random random = RandomSingleton.getInstance();

        for (int i = 0; i < 10; i++) {
            double doubleAmount = random.nextDouble(20, 50);

            Decimal2f amount = Decimal2f.valueOf(doubleAmount);

            System.out.println(Color.RED.colorize("Withdrawing: ") + amount);

            try {
                bankAccount.withdraw(amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Color.YELLOW.colorize("New balance: ") + bankAccount.getBalance());
        }
    }
}

