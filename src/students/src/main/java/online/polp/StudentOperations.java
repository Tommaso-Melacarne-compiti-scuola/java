package online.polp;

import java.util.List;
import java.util.Map;

public interface StudentOperations {
    /**
     * Add a grade to a student
     *
     * @param studentName the name of the student
     * @param subject     the subject of the grade
     * @param grade       the grade
     */
    void addGradeToStudent(String studentName, String subject, Double grade);

    /**
     * Add a student to the list
     *
     * @param student the student to add
     */
    void addStudent(Student student);

    /**
     * Get the average of all students
     *
     * @return A map with the student name as the key and the average as the value
     */
    Map<String, Double> getAverage();

    /**
     * Sort the students by name
     */
    List<Student> getSortedByName();
}
