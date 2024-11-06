package online.polp.product;

public enum ProductMaterialType {
    // Recyclable materials
    PAPER(true),
    GLASS(true),
    PLASTIC(true),

    // Non-recyclable materials
    METAL(false),
    WOOD(false),
    FABRIC(false);

    private final boolean recyclable;

    ProductMaterialType(boolean recyclable) {
        this.recyclable = recyclable;
    }

    public boolean isRecyclable() {
        return recyclable;
    }

    @Override
    public String toString() {
        return recyclable ? "Recyclable" : "Non-recyclable";
    }
}
