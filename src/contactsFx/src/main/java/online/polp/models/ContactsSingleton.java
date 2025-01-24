package online.polp.models;

public class ContactsSingleton {
    private static ContactsManager INSTANCE;

    public static ContactsManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactsManager();

            INSTANCE.addContact(new Contact("John Doe", "123456789"));
            INSTANCE.addContact(new Contact("Jane Doe", "987654321"));
        }

        return INSTANCE;
    }
}
