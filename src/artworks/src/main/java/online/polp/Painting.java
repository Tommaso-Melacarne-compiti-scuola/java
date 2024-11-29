package online.polp;

public class Painting extends AbstractArtwork {
    private String medium;


    @SuppressWarnings("unused")
    public Painting() {
    }

    @Override
    public String getType() {
        return "painting";
    }

    public Painting(String title, String artist, int year, String medium) {
        super(title, artist, year);
        this.medium = medium;
    }

    @SuppressWarnings("unused")
    public String getMedium() {
        return medium;
    }

    @Override
    public String toString() {
        return "Painting: title=" + getTitle() + ", artist=" + getArtist() + ", year=" + getYear() + ", medium=" + medium;
    }
}
