package online.polp;

@SuppressWarnings("UnnecessaryModifier")
public enum ProductType {
    NON_FOOD("Non-food"),
    FOOD("Food");

    private final String type;

    private ProductType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
