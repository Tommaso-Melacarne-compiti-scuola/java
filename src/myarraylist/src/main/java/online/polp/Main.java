package online.polp;

public class Main {
    public static void main(String[] args) {
        // void add(T gen)
        //void add(int posizione, T gen) // con shift degli elementi presenti a destra
        //boolean remove(int posizione)
        //
        //// con shift degli elementi presenti a sinistra
        //boolean contains(T gen)
        //T get(int posizione)
        //set(int posizione, T gen)
        //int size()
        //boolean isEmpty()
        //int indexOf(T gen)`

        MyArrayList<String> list = new MyArrayList<>();

        list.add("World");

        list.add(0, "Mello");

        System.out.println(list);

        list.remove(0);

        System.out.println(list);

        System.out.println(list.contains("World"));

        System.out.println(list.get(0));

        list.set(0, "Hello");

        System.out.println(list);

        System.out.println(list.size());

        System.out.println(list.isEmpty());

        System.out.println(list.indexOf("Hello"));

    }
}