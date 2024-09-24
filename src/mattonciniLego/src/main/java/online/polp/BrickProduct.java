package online.polp;

import java.util.ArrayList;
import java.util.List;

public class BrickProduct {
    private final List<Brick> bricks = new ArrayList<>();

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public int bricksCount() {
        return bricks.size();
    }

    public int bricksWeight() {
        return bricks
                .stream()
                .mapToInt(Brick::getWeight)
                .sum();
    }

    public List<Brick> getBricksFilteredByColor(Color color) {
        return bricks
                .stream()
                .filter(brick -> brick.getColor().equals(color))
                .toList();
    }

    public List<Brick> getBricksById(int id) {
        return bricks
                .stream()
                .filter(brick -> brick.getId() == id)
                .toList();
    }

    public void setBricksColorById(int id, Color color) {
        bricks
                .stream()
                .filter(brick -> brick.getId() == id)
                .forEach(brick -> brick.setColor(color));
    }

    @Override
    public String toString() {
        return "BrickProduct{" +
                "bricks=" + bricks +
                '}';
    }
}
