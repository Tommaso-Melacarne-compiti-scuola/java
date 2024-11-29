package online.polp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Installation {
    private Map<String, List<AbstractArtwork>> artistsWithArtworks = new HashMap<>();
    private int numberOfPeople = 0;
    private int durationInMinutes = 0;

    public Installation() {
    }

    public Installation(Map<String, List<AbstractArtwork>> artistsWithArtworks, int numberOfPeople, int durationInMinutes) {
        this.artistsWithArtworks = artistsWithArtworks;
        this.numberOfPeople = numberOfPeople;
        this.durationInMinutes = durationInMinutes;
    }

    public Map<String, List<AbstractArtwork>> getArtistsWithArtworks() {
        return artistsWithArtworks;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setArtistsWithArtworks(Map<String, List<AbstractArtwork>> artistsWithArtworks) {
        this.artistsWithArtworks = artistsWithArtworks;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
