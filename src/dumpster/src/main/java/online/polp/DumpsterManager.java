package online.polp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DumpsterManager {
    List<Dumpster> dumpsters = new ArrayList<>();

    public void addDumpster(Dumpster dumpster) {
        dumpsters.add(dumpster);
    }

    public void removeDumpsterById(int id) {
        dumpsters.removeIf(dumpster -> dumpster.getId() == id);
    }

    public List<Dumpster> getDumpsters() {
        return dumpsters;
    }

    public Optional<Dumpster> getDumpsterById(int id) {
        return dumpsters
                .stream()
                .filter(dumpster -> dumpster.getId() == id)
                .findFirst();
    }

    public void updateDumpsterEmptyingTimeById(int id, LocalDateTime time) {
        dumpsters
                .stream()
                .filter(dumpster -> dumpster.getId() == id)
                .findFirst()
                .ifPresent(dumpster -> dumpster.setLastEmptied(time));
    }

    public List<Dumpster> getDumpstersByType(DumpsterType type) {
        return dumpsters
                .stream()
                .filter(dumpster -> dumpster.getType() == type)
                .toList();
    }

    public List<Dumpster> getDumpstersByLocationArea(Location location1, Location location2) {
        return dumpsters
                .stream()
                .filter(dumpster -> dumpster.getLocation().isWithinArea(location1, location2))
                .toList();
    }

}
