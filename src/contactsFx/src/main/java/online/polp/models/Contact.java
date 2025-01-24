package online.polp.models;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String phone;
    private static int nextId = 0;
    private final int id;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.id = ++nextId;
    }
}