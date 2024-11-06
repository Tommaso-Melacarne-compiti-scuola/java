package online.polp;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Fridge fridge = new Fridge();
        /*
        inserimento di un nuovo prodotto nel frigorifero;
        prelevamento di un prodotto dal frigorifero specificando il codice identificativo e la data di scadenza;
        elenco di tutti i prodotti presenti nel frigorifero;
        elenco dei prodotti scaduti presenti nel frigorifero;
        calcolo del numero di confezioni di prodotto presenti nel frigorifero a partire dal codice identificativo;
        salvataggio/ripristino dei prodotti su/da file.
         */

        LocalDate now = LocalDate.now();

        // Inserimento di un nuovo prodotto nel frigorifero
        fridge.addProduct(new Product(1, "Latte", now.plusDays(10), 100));
        fridge.addProduct(new Product(2, "Burro", now.plusDays(5), 200));
        fridge.addProduct(new Product(3, "Uova", now.plusDays(15), 300));
        fridge.addProduct(new Product(3, "Uova", now.plusDays(30), 300));

        // Prelevamento di un prodotto dal frigorifero specificando il codice identificativo e la data di scadenza
        System.out.println("Latte prelevato dal frigorifero:");
        LocalDate milkExpiry = now.plusDays(10);
        Optional<Product> product = fridge.getProductByIdAndExpiryDate(1, milkExpiry);
        product.ifPresent(System.out::println);

        // Elenco di tutti i prodotti presenti nel frigorifero
        System.out.println("Elenco di tutti i prodotti presenti nel frigorifero:");
        fridge.getProductList().forEach(System.out::println);

        // Elenco dei prodotti scaduti presenti nel frigorifero
        System.out.println("Elenco dei prodotti scaduti presenti nel frigorifero:");
        fridge.getExpiredProducts(now);

        // Calcolo del numero di confezioni di prodotto presenti nel frigorifero a partire dal codice identificativo
        long eggCount = fridge.countWithSameId(3);
        System.out.println("Numero di confezioni di prodotto con id 3 presenti nel frigorifero: " + eggCount);

        // Salvataggio/ripristino dei prodotti su/da file
        try {
            String filename = "fridge.json";

            fridge.toDisk(filename);
            System.out.println("Frigorifero salvato su file: " + filename);

            Fridge restoredFridge = Fridge.fromDisk(filename);
            System.out.println("Frigorifero ripristinato da file: " + filename);

            System.out.println("Elenco di tutti i prodotti presenti nel frigorifero ripristinato:");
            restoredFridge.getProductList().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}