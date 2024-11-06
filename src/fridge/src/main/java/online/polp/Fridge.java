package online.polp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Fridge implements Serializable {
    final List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public Optional<Product> getProductByIdAndExpiryDate(int id, LocalDate expiryDate) {
        return productList
                .stream()
                .filter(product -> product.id() == id && product.expiryDate().equals(expiryDate))
                .findFirst();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void getExpiredProducts(LocalDate now) {
        productList
                .stream()
                .filter(product -> product.expiryDate().isBefore(now))
                .forEach(System.out::println);
    }

    public long countWithSameId(int id) {
        return productList
                .stream()
                .filter(product -> product.id() == id)
                .count();
    }

    public void toDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        mapper.writeValue(new File(filename), this);
    }

    public static Fridge fromDisk(String filename) throws IOException {
        ObjectMapper mapper = ObjectMapperSingleton.getInstance();
        return mapper.readValue(new File(filename), Fridge.class);
    }
}
