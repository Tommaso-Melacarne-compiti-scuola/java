package online.polp.product;

import online.polp.ProductType;

import java.time.LocalDateTime;

public class NonFoodProduct extends Product {
    private final ProductMaterialType materialType;

    public NonFoodProduct(String description, String price, ProductMaterialType materialType) {
        super(description, price);
        this.materialType = materialType;
    }

    @Override
    public ProductType getProductType() {
        return ProductType.NON_FOOD;
    }

    @Override
    public boolean isEligibleForDiscount(LocalDateTime now) {
        return materialType.isRecyclable();
    }
}
