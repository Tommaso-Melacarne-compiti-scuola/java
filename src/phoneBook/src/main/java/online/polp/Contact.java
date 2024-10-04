package online.polp;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;

    public Contact(String name, String email, String phone) {
        this.firstName = name;
        this.lastName = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "Name: " + firstName + ", Email: " + lastName + ", Phone: " + phone;
    }
}
