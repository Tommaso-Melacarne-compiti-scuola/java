package online.polp;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("cane", "dog");
        dictionary.addWord("gatto", "cat");

        System.out.println(dictionary.translate("cane"));
        System.out.println(dictionary.translate("gatto"));

        System.out.println(dictionary.translate("topo"));
    }
}