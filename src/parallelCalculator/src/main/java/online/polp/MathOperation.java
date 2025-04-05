package online.polp;

public interface MathOperation<T extends Number> {
    T calculate(T a, T b);
}
