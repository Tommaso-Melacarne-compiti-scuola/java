package online.polp;

public abstract class Developer implements Comparable<Developer> {
    private String ssn;
    private String firstName;
    private String lastName;
    private double totalCompensation;
    private Curriculum curriculum;

    public Developer(String ssn, String firstName, String lastName, int totalCompensation, Curriculum curriculum) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalCompensation = totalCompensation;
        this.curriculum = curriculum;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotalCompensation() {
        return totalCompensation;
    }


    public void setTotalCompensation(int totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public String toString() {
        return "CF: " + ssn + "\n" +
                "Nome: " + firstName + "\n" +
                "Cognome: " + lastName + "\n" +
                "Stipendio: " + totalCompensation + "\n" +
                "Curriculum: " + curriculum.getNumPages() + " - " + curriculum.getSchool() + " - " + curriculum.getLanguages();
    }

    abstract public DeveloperType getType();

    public void promote() {
        switch (this.getType()) {
            case Mobile -> this.totalCompensation += this.totalCompensation * 0.1;
            case Cloud -> this.totalCompensation += this.totalCompensation * 0.2;
            case null, default -> {
            }
        }
    }

    public boolean learnNewLanguage(String language) {
        return curriculum.addLanguage(language);
    }

    public String getDeveloperValue() {
        int value = curriculum.calculateValue();
        return switch (value) {
            case 1 -> "intern";
            case 2 -> "junior";
            case 3 -> "senior";
            default -> "unknown";
        };
    }

    @Override
    public int compareTo(Developer other) {
        return Double.compare(this.totalCompensation, other.totalCompensation);
    }
}
