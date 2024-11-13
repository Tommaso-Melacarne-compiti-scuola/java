package online.polp;

import java.util.ArrayList;
import java.util.Optional;

public class Curriculum {
    private int numPages;
    private String school;
    private ArrayList<String> languages;

    public Curriculum(int numPages, String school) {
        this.numPages = numPages;
        this.school = school;
        this.languages = new ArrayList<String>();
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean addLanguage(String language) {
        if (languages.size() >= 6 || languages.contains(language)) {
            return false;
        }
        languages.add(language);
        return true;
    }

    public int calculateValue() {
        if (languages.size() <= 1) {
            return 1;
        } else if (languages.size() <= 4) {
            return 2;
        } else {
            return 3;
        }
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String toString() {
        return "Curriculum: " + numPages + " pages, " + school + ", " + languages;
    }
}
