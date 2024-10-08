package online.polp;

import java.util.ArrayList;
import java.util.List;

public class TagManager {
    private final ArrayList<Tag> tags;

    public TagManager() {
        tags = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTagById(String uid) {
        tags.removeIf(tag -> tag.getUid().equals(uid));
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public List<Tag> getTagsInProximity(Point point, double distance) {
        return this.tags
                .stream()
                .filter(tag -> tag.getLocation().distanceFrom(point) <= distance)
                .toList();
    }
}