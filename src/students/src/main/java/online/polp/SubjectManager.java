package online.polp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SubjectManager {
    private final Map<String, List<Double>> grades = new HashMap<>();

    public SubjectManager() {
    }

    public void addGrade(String subject, Double grade) {
        grades.putIfAbsent(subject, new ArrayList<>());

        grades.get(subject).add(grade);
    }

    /**
     * Returns a map with the subject and its average
     *
     * @return A map with the subject as the key and its average as the value
     */
    private Map<String, Double> getSubjectAndItsAverage() {
        return grades
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e
                                        .getValue()
                                        .stream()
                                        .mapToDouble(Double::doubleValue)
                                        .average()
                                        .orElseThrow(() -> new GradesException("No grades found"))
                        )
                );
    }


    /**
     * Returns the global average of all subjects
     *
     * @return The global average of all subjects
     */
    public Double getGlobalAverage() {
        return this
                .getSubjectAndItsAverage()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(() -> new GradesException("No subjects found"));
    }

    @Override
    public String toString() {
        return "SubjectManager{" +
                "grades=" + grades +
                '}';
    }
}
