package online.polp;

import online.polp.pojos.Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addStack(MyStack<Book> stack) {
        while (!stack.isEmpty()) {
            //noinspection OptionalGetWithoutIsPresent
            addBook(stack.pop().get());
        }
    }

    public void fromBiblioteca(Biblioteca biblioteca) {
        biblioteca
                .scaffale()
                .libri()
                .stream()
                .map(Book::fromLibro)
                .forEach(this::addBook);
    }
}
