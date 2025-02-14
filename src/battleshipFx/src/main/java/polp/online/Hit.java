package polp.online;

import lombok.*;
import polp.online.model.AttackResult;
import polp.online.model.Point;

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
