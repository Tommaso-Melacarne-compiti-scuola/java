package online.polp;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EmergencyRoom emergencyRoom = new EmergencyRoom();

        LocalDate now = LocalDate.now();

        emergencyRoom.add(
            Priority.RED,
            new Patient("John", "Doe", now.minusYears(50), "123-45-6789")
        );
        emergencyRoom.add(
            Priority.YELLOW,
            new Patient("Jane", "Smith", now.minusYears(30), "987-65-4321")
        );
        emergencyRoom.add(
            Priority.WHITE,
            new Patient("Alice", "Brown", now.minusYears(70), "246-80-1357")
        );
        emergencyRoom.add(
            Priority.RED,
            new Patient("Bob", "White", now.minusYears(60), "135-79-0246")
        );

        System.out.println(emergencyRoom.pop());
        System.out.println(emergencyRoom.pop());
        System.out.println(emergencyRoom.pop());
        System.out.println(emergencyRoom.pop());

        System.out.println("Average time in queue: " + emergencyRoom.getAverageTimeInQueue().toMillis() + " milliseconds");
    }
}