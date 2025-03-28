package polp.online.pojos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SellTicketResponseType {
    SUCCESS("Success"),
    SOLD_OUT("Sold Out");

    private final String message;
}
