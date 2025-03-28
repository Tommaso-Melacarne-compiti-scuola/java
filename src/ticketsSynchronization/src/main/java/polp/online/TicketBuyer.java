package polp.online;

import lombok.RequiredArgsConstructor;
import polp.online.pojos.SellTicketResponseType;
import polp.online.pojos.Ticket;
import polp.online.pojos.requests_responses.SellTicketRequest;
import polp.online.pojos.requests_responses.SellTicketResponse;

import java.util.UUID;

@RequiredArgsConstructor
public class TicketBuyer implements Runnable {
    private final TicketOffice ticketOffice;
    private final UUID uuid;

    @Override
    public void run() {
        SellTicketResponse ticketResponse = ticketOffice.sellTicket(new SellTicketRequest(uuid));

        if (ticketResponse.getType().equals(SellTicketResponseType.SUCCESS)) {
            System.out.println("Ticket sold to " + uuid);
        } else {
            System.out.println("Could not sell ticket to " + uuid);
        }
    }
}
