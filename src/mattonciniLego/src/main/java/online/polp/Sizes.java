package online.polp;

public class Sizes {
    private final int weight;
    private final int length;
    private final int width;
    private final int height;

    public Sizes(int weight, int length, int width, int height) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Sizes{" +
                "weight=" + weight +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
