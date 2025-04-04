package online.polp;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<Double> randomDoubleSupplier = () -> (Math.random() * 100);
        Consumer<String> printConsumer = System.out::println;

        Double a = randomDoubleSupplier.get();
        Double b = randomDoubleSupplier.get();
        printConsumer.accept("Calculating numbers: " + a + " and " + b);

        //noinspection Convert2MethodRef
        Double sum = Calculator.calculate(a, b, (x, y) -> x + y);
        Double difference = Calculator.calculate(a, b, (x, y) -> x - y);
        Double product = Calculator.calculate(a, b, (x, y) -> x * y);
        Double quotient = Calculator.calculate(a, b, (x, y) -> x / y);

        printConsumer.accept("Sum: " + sum);
        printConsumer.accept("Difference: " + difference);
        printConsumer.accept("Product: " + product);
        printConsumer.accept("Quotient: " + quotient);
    }
}