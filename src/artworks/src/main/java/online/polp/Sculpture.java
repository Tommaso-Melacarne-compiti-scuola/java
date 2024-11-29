package online.polp;

public class Sculpture extends AbstractArtwork {
    private String material;

    @SuppressWarnings("unused")
    public Sculpture() {
    }

    @Override
    public String getType() {
        return "sculpture";
    }

    public Sculpture(String title, String artist, int year, String material) {
        super(title, artist, year);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Sculpture: title=" + getTitle() + ", artist=" + getArtist() + ", year=" + getYear() + ", material=" + material;
    }
}
