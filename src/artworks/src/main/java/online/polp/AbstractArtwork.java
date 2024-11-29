package online.polp;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sculpture.class, name = "sculpture"),
        @JsonSubTypes.Type(value = Painting.class, name = "painting")
})
public abstract class AbstractArtwork {
    private String title;
    private String artist;
    private int year;

    public AbstractArtwork() {
    }

    protected AbstractArtwork(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    @SuppressWarnings("unused")
    public abstract String getType();

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }
}
