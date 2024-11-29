package online.polp;

import online.polp.singletons.BufferedReaderSingleton;

import java.io.BufferedReader;
import java.io.IOException;

public class ArtworkMenu {
    static final String filename = "artworks.json";

    public static void displayMenuOptions() {
        System.out.println("Artwork Menu");
        System.out.println("1. Add a new sculpture");
        System.out.println("2. Add a new painting");
        System.out.println("3. List all artworks");
        System.out.println("4. Save artworks to disk");
        System.out.println("5. Load artworks from disk");
        System.out.println("0. Exit");
    }

    public static void addDefaultArtworks(ArtworkManager manager) {
        manager.addArtwork(new Sculpture("David", "Michelangelo", 1504, "Marble"));
        manager.addArtwork(new Sculpture("Pieta", "Michelangelo", 1499, "Marble"));
        manager.addArtwork(new Painting("Mona Lisa", "Leonardo da Vinci", 1503, "Oil"));
        manager.addArtwork(new Painting("The Last Supper", "Leonardo da Vinci", 1498, "Fresco"));
        manager.addArtwork(new Sculpture("Horse and Rider", "Leonardo da Vinci", 1482, "Clay"));
    }

    public static void showMenu() {
        ArtworkManager manager = new ArtworkManager();
        BufferedReader inputReader = BufferedReaderSingleton.getInstance();

        addDefaultArtworks(manager);

        while (true) {
            displayMenuOptions();

            try {
                int option = Integer.parseInt(inputReader.readLine());

                switch (option) {
                    case 1 -> manager.addArtwork(getSculpture());
                    case 2 -> manager.addArtwork(getPainting());
                    case 3 -> listArtworks(manager);
                    case 4 -> {
                        try {
                            manager.toDisk(filename);
                        } catch (IOException e) {
                            System.out.println("Error saving to disk");
                            e.printStackTrace();
                        }
                    }
                    case 5 -> {
                        try {
                            manager = ArtworkManager.fromDisk(filename);
                        } catch (IOException e) {
                            if (e.getMessage().contains("No such file or directory")) {
                                System.out.println("File not found");
                            } else {
                                System.out.println("Error loading from disk");
                                e.printStackTrace();
                            }
                        }
                    }
                    case 0 -> System.exit(0);
                    default -> System.out.println("Invalid option");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Sculpture getSculpture() throws IOException {
        BufferedReader inputReader = BufferedReaderSingleton.getInstance();

        System.out.println("Enter the title of the sculpture:");
        String title = inputReader.readLine();

        System.out.println("Enter the artist of the sculpture:");
        String artist = inputReader.readLine();

        System.out.println("Enter the year when the sculpture was made:");
        int year = Integer.parseInt(inputReader.readLine());

        System.out.println("Enter the material of the sculpture:");
        String material = inputReader.readLine();

        return new Sculpture(title, artist, year, material);
    }

    public static Painting getPainting() throws IOException {
        BufferedReader inputReader = BufferedReaderSingleton.getInstance();

        System.out.println("Enter the title of the painting:");
        String title = inputReader.readLine();

        System.out.println("Enter the artist of the painting:");
        String artist = inputReader.readLine();

        System.out.println("Enter the year when the painting was made:");
        int year = Integer.parseInt(inputReader.readLine());

        System.out.println("Enter the medium of the painting:");
        String medium = inputReader.readLine();

        return new Painting(title, artist, year, medium);
    }

    public static void listArtworks(ArtworkManager manager) {
        System.out.println(manager);
    }
}
