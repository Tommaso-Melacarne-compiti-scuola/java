package online.polp.school;

public class Person {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                '}';
    }
}
