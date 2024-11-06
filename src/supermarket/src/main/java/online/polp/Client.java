package online.polp;

public record Client(boolean hasCard, ShoppingList shoppingList) {
    @Override
    public String toString() {
        return "Client " + (hasCard ? "with card" : "without card") + "\n" + shoppingList;
    }
}
