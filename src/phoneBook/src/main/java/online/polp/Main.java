package online.polp;

// DISCLAIMER:
// This is a work of imagination
// Every reference to people or events in this code is purely coincidental

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact(new Contact("Bassimo", "Mossetti", "1234567890"));
        phoneBook.addContact(new Contact("Tilippo", "Furetta", "0987654321"));
        phoneBook.addContact(new Contact("Ciulia", "Gecchetin", "2345678901"));
        phoneBook.addContact(new Contact("Pietro", "Pacciani", "6789054321"));
        phoneBook.addContact(new Contact("Sapitan", "Cchettino", "2345678901"));

        System.out.println(phoneBook);

        phoneBook.removeContact("Ciulia", "Gecchetin");

        System.out.println(phoneBook);

        System.out.println(phoneBook.searchContact("Pietro", "Pacciani")); // 6789054321
        System.out.println(phoneBook.searchContact("Ciulia", "Gecchetin")); // null
    }
}