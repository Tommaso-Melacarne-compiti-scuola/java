package online.polp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Library<Readable> library = new Library<>();

        LocalDate now = LocalDate.now();

        //1. Aggiungere un oggetto alla collezione.
        library.add(new Book("Il signore degli anelli", "J.R.R. Tolkien"));
        library.add(new Magazine("Focus", now.minusMonths(1)));
        library.add(new Newspaper("Corriere della sera", now.minusDays(1)));


        //2. Rimuovere un oggetto per titolo. Eccezione se non esiste.
        try {
            library.removeByTitle("Focus");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

        //3. Visualizzare un elenco ordinato (TreeSet) dei titoli disponibili.
        library.getElementsSorted().forEach(System.out::println);

        //4. Cercare un oggetto per titolo
        Optional<Readable> corriereDellaSera = library.getByTitle("Corriere della sera");
        corriereDellaSera.ifPresent(System.out::println);

        //5. Usare una HashMap per categorizzare gli oggetti (es. libri, riviste, ecc.).
        HashMap<ReadableType, List<Readable>> byType = library.getByType();

        byType.forEach((type, list) -> {
            System.out.println(type);
            list.forEach(System.out::println);
        });
    }
}