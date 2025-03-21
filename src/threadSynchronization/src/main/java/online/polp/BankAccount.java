package online.polp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.decimal4j.immutable.Decimal2f;
import org.decimal4j.mutable.MutableDecimal2f;

@Getter
@RequiredArgsConstructor
public class BankAccount {
    private final MutableDecimal2f balance;

    public synchronized void deposit(Decimal2f amount) {
        balance.add(amount);
        notifyAll();
    }

    public synchronized void withdraw(Decimal2f amount) throws InterruptedException {
        if (balance.compareTo(amount) < 0) {
            System.out.println("Insufficient funds. Waiting for deposit...");
            wait();
        }

        balance.subtract(amount);
    }
}
