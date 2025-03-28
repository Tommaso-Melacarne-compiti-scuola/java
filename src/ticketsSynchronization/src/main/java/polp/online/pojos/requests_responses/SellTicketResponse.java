package polp.online.pojos.requests_responses;

import lombok.*;
import polp.online.pojos.SellTicketResponseType;
import polp.online.pojos.Ticket;

@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class SellTicketResponse {
    private final @NonNull SellTicketResponseType type;
    private final Ticket ticket;
}
