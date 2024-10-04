package online.polp;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String firstName, String lastName) {
        contacts.removeIf(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName));
    }

    public String searchContact(String firstName, String lastName) {
        return contacts
                .stream()
                .filter(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName))
                .map(Contact::getPhone)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "contacts=" + contacts +
                '}';
    }
}
