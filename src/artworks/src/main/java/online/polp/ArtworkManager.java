package online.polp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.polp.singletons.ObjectMapperSingleton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtworkManager {
    private Map<String, List<AbstractArtwork>> artistsWithArtworks = new HashMap<>();

    public ArtworkManager() {
    }

    public ArtworkManager(Map<String, List<AbstractArtwork>> artistsWithArtworks) {
        this.artistsWithArtworks = artistsWithArtworks;
    }

    public Map<String, List<AbstractArtwork>> getArtistsWithArtworks() {
        return artistsWithArtworks;
    }

    public void addArtwork(AbstractArtwork artwork) {
        artistsWithArtworks.putIfAbsent(artwork.getArtist(), new ArrayList<>());
        artistsWithArtworks.get(artwork.getArtist()).add(artwork);
    }

    public List<AbstractArtwork> getArtworksByArtist(String artist) {
        return artistsWithArtworks.get(artist);
    }

    public void toDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        mapper.writeValue(new File(filename), artistsWithArtworks);
    }

    public static ArtworkManager fromDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        Map<String, List<AbstractArtwork>> artworks = mapper.readValue(new File(filename), new TypeReference<>() {
        });
        return new ArtworkManager(artworks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        artistsWithArtworks.forEach((artist, artworks) -> {
            sb.append(artist).append(":\n");
            artworks.forEach(artwork -> sb.append(artwork.toString()).append("\n"));
        });

        return sb.toString();
    }
}
