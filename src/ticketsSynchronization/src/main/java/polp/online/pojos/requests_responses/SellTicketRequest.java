package polp.online.pojos.requests_responses;

import lombok.Data;

import java.util.UUID;

@Data
public class SellTicketRequest {
    private final UUID uuid;
}
