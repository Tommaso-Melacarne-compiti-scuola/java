package online.polp.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ContactsManager {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContactById(int id) {
        contacts.removeIf(contact -> contact.getId() == id);
    }

    public List<Contact> getSortedContactsByName() {
        contacts.sort(Comparator.comparing(Contact::getName));

        return contacts;
    }

    public List<Contact> getSortedFilteredList(String filter) {
        List<Contact> sortedContacts = this.getSortedContactsByName();

        if (filter == null || filter.isEmpty()) {
            return sortedContacts;
        }

        return sortedContacts
            .stream()
            .filter(contact -> contact.getName().toLowerCase().contains(filter.toLowerCase()))
            .collect(Collectors.toList());
    }
}
