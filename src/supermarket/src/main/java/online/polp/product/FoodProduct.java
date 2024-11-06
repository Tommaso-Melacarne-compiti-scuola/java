package online.polp.product;

import online.polp.ProductType;

import java.time.LocalDateTime;

public class FoodProduct extends Product {
    private final LocalDateTime expirationDate;

    public FoodProduct(String description, String price, LocalDateTime expirationDate) {
        super(description, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.FOOD;
    }

    private boolean isExpirationDateCloserThan(LocalDateTime now, int days) {
        int daysUntilExpiration = this.expirationDate.getDayOfYear() - now.getDayOfYear();
        return daysUntilExpiration <= days;
    }

    @Override
    public boolean isEligibleForDiscount(LocalDateTime now) {
        int daysToConsiderExpirationDateClose = 10;

        return isExpirationDateCloserThan(now, daysToConsiderExpirationDateClose);
    }
}
