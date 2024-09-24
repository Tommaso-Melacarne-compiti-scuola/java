package online.polp;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Brick {
    private Color color = new Color(255, 255, 255);

    private final int id;
    private final Sizes sizes;

    public Brick(int id, Sizes sizes) {
        this.id = id;
        this.sizes = sizes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return sizes.getWeight();
    }

    @Override
    public String toString() {
        return "Brick{" +
                "color=" + color +
                ", id=" + id +
                ", sizes=" + sizes +
                '}';
    }
}
