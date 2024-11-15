package online.polp;

import java.util.Objects;

public class Student {
    private final String name;
    private final SubjectManager subject = new SubjectManager();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getAverageGrade() {
        return subject.getGlobalAverage();
    }

    public void addGrade(String subject, Double grade) {
        this.subject.addGrade(subject, grade);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subject);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subject=" + subject +
                '}';
    }
}
