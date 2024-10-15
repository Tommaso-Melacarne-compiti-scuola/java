package online.polp;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DumpsterManager dumpsterManager = new DumpsterManager();

        LocalDateTime now = LocalDateTime.now();

        // Adding a new dumpster
        dumpsterManager.addDumpster(
                new Dumpster(DumpsterType.FABRIC, new Location(50.0, 50.0), now.minusDays(1),
                        new Volume(1.0, 1.0, 1.0)
                )
        );
        dumpsterManager.addDumpster(
                new Dumpster(DumpsterType.GLASS, new Location(50.0, 50.0), now.minusDays(2),
                        new Volume(1.0, 1.0, 1.0)
                )
        );
        dumpsterManager.addDumpster(
                new Dumpster(DumpsterType.PAPER, new Location(50.0, 50.0), now.minusDays(3),
                        new Volume(1.0, 1.0, 1.0)
                )
        );

        System.out.println(dumpsterManager.getDumpsters());

        // Removing a dumpster by id
        dumpsterManager.removeDumpsterById(1);
        System.out.println(dumpsterManager.getDumpsters());

        // Updating a dumpster's emptying time by id
        dumpsterManager.updateDumpsterEmptyingTimeById(2, now);
        System.out.println(dumpsterManager.getDumpsterById(2));

        // Getting dumpsters by type
        List<Dumpster> glassDumpsters = dumpsterManager.getDumpstersByType(DumpsterType.GLASS);
        System.out.println(glassDumpsters);
    }
}