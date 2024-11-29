package online.polp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.polp.singletons.ObjectMapperSingleton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtworkManager {
    private final Installation installation;

    public ArtworkManager() {
        installation = new Installation();
    }

    public ArtworkManager(Installation installation) {
        this.installation = installation;
    }

    public void addArtwork(AbstractArtwork artwork) {
        installation.getArtistsWithArtworks().putIfAbsent(artwork.getArtist(), new ArrayList<>());

        installation.getArtistsWithArtworks().get(artwork.getArtist()).add(artwork);
    }

    public List<AbstractArtwork> getArtworksByArtist(String artist) {
        return installation.getArtistsWithArtworks().get(artist);
    }

    public List<AbstractArtwork> getArtworksByTitle(String title) {
        return installation
                .getArtistsWithArtworks()
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(artwork -> artwork.getTitle().equals(title))
                .toList();
    }

    public List<AbstractArtwork> getArtworksByYear(int year) {
        return installation.getArtistsWithArtworks()
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(artwork -> artwork.getYear() == year)
                .toList();
    }

    public void toDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        mapper.writeValue(new File(filename), installation);
    }

    public static ArtworkManager fromDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        Installation installation = mapper.readValue(new File(filename), new TypeReference<>() {
        });
        return new ArtworkManager(installation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        installation.getArtistsWithArtworks().forEach((artist, artworks) -> {
            sb.append(artist).append(":\n");
            artworks.forEach(artwork -> sb.append(artwork.toString()).append("\n"));
        });

        return sb.toString();
    }
}
