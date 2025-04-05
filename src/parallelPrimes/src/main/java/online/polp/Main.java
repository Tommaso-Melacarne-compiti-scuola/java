package online.polp;

import java.util.List;

public class Main {
    public static final int START = 1;
    public static final int END = 100000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List<Integer> primes = PrimeFinder.findPrimes(START, END);

        long timeTaken = System.currentTimeMillis() - start;

        System.out.println("Primes: " + primes);
        System.out.println("Found " + primes.size() + " primes in the range [" + START + ", " + END + "]");
        System.out.println("Took " + timeTaken + " ms");
    }
}


