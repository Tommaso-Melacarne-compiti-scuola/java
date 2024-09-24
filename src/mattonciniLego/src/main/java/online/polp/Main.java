package online.polp;

public class Main {
    public static void main(String[] args) {
        // Test the BrickManager class
        BrickProduct brickProduct = new BrickProduct();
        brickProduct.addBrick(new Brick(1, new Sizes(10, 10, 10, 10)));
        brickProduct.addBrick(new Brick(2, new Sizes(20, 20, 20, 20)));
        brickProduct.addBrick(new Brick(3, new Sizes(30, 30, 30, 30)));

        System.out.println("Bricks count: " + brickProduct.bricksCount());
        System.out.println("Bricks weight: " + brickProduct.bricksWeight());

        brickProduct.setBricksColorById(2, new Color(255, 0, 0));

        Color colorToFilter = new Color(255, 0, 0);
        System.out.println("Bricks with color " + colorToFilter + ": " + brickProduct.getBricksFilteredByColor(colorToFilter));

        int idToFilter = 3;
        var bricksById = brickProduct.getBricksById(idToFilter);
        System.out.println("Bricks with id " + idToFilter + ": " + bricksById);
        System.out.println("Brick product: " + brickProduct);
    }
}