package online.polp;

public class Calculator {
    public static <T extends Number> T calculate(T a, T b, MathematicalOperation<T> operation) {
        return operation.calculate(a, b);
    }
}
