package online.polp;

import java.util.*;

public class Library<T extends Readable> {
    List<T> libraryElements = new ArrayList<>();

    public List<T> getLibraryElements() {
        return libraryElements;
    }

    public void add(T element) {
        libraryElements.add(element);
    }

    public void removeByTitle(String title) throws BookNotFoundException {
        boolean removed = false;

        libraryElements.removeIf(element -> {
            boolean remove = element.title().equals(title);

            if (remove) {
                removed = true;
            }

            return remove;
        });

        if (!removed) {
            throw new BookNotFoundException();
        }
    }

    public TreeSet<T> getElementsSorted() {
        TreeSet<T> sorted = new TreeSet<>(Comparator.comparing(Readable::title));
        sorted.addAll(libraryElements);
        return sorted;
    }

    public Optional<T> getByTitle(String title) {
        return libraryElements
                .stream()
                .filter(element -> element.title().equals(title))
                .findFirst();
    }

    public HashMap<ReadableType, List<T>> getByType() {
        HashMap<ReadableType, List<T>> map = new HashMap<>();

        for (T element : libraryElements) {
            map.putIfAbsent(element.getType(), new ArrayList<>());

            List<T> list = map.get(element.getType());

            list.add(element);
        }

        return map;
    }


}
