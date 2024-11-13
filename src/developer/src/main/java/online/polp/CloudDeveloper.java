package online.polp;

public class CloudDeveloper extends Developer {
    private String cloud;
    private int yearsOfExperience;

    public CloudDeveloper(String ssn, String firstName, String lastName, int totalCompensation, Curriculum curriculum, String cloud, int yearsOfExperience) {
        super(ssn, firstName, lastName, totalCompensation, curriculum);
        this.cloud = cloud;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return super.toString() + ", cloud: " + cloud + ", years of experience: " + yearsOfExperience;
    }

    @Override
    public DeveloperType getType() {
        return DeveloperType.Cloud;
    }
}
