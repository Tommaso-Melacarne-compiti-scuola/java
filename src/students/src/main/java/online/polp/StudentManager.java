package online.polp;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManager implements StudentOperations {
    List<Student> students = new ArrayList<>();

    @Override
    public void addGradeToStudent(String studentName, String subject, Double grade) {
        Student student = students
                .stream()
                .filter((s) -> s.getName().equals(studentName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        student.addGrade(subject, grade);
    }

    @Override
    public void addStudent(Student student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student already exists");
        }

        students.add(student);
    }

    @Override
    public Map<String, Double> getAverage() {
        return students
                .stream()
                .collect(
                        Collectors.toMap(
                                Student::getName,
                                Student::getAverageGrade
                        )
                );
    }

    @Override
    public List<Student> getSortedByName() {
        students.sort(Comparator.comparing(Student::getName));

        return students;
    }
}
