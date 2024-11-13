package online.polp;

public class MobileDeveloper extends Developer {
    private String preferredSystem;
    private boolean nativeFramework;

    public MobileDeveloper(String ssn, String firstName, String lastName, int totalCompensation, Curriculum curriculum, String preferredSystem, boolean nativeFramework) {
        super(ssn, firstName, lastName, totalCompensation, curriculum);
        this.preferredSystem = preferredSystem;
        this.nativeFramework = nativeFramework;
    }

    public String getPreferredSystem() {
        return preferredSystem;
    }

    public void setPreferredSystem(String preferredSystem) {
        this.preferredSystem = preferredSystem;
    }

    public boolean isNativeFramework() {
        return nativeFramework;
    }

    public void setNativeFramework(boolean nativeFramework) {
        this.nativeFramework = nativeFramework;
    }

    @Override
    public String toString() {
        return super.toString() + ", preferred system: " + preferredSystem + ", native framework: " + nativeFramework;
    }

    @Override
    public DeveloperType getType() {
        return DeveloperType.Mobile;
    }
}
