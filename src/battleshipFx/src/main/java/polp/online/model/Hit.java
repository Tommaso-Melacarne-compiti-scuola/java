package polp.online.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
public class Hit extends Point {
    @NonNull
    private AttackResult result;

    public Hit(AttackResult result, Point point) {
        super(point);
        this.result = result;
    }
}
