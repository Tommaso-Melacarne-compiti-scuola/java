package online.polp;

public interface MathematicalOperation<T extends Number> {
    T calculate(T a, T b);
}
