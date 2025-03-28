package polp.online;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import polp.online.pojos.SellTicketResponseType;
import polp.online.pojos.Ticket;
import polp.online.pojos.requests_responses.SellTicketRequest;
import polp.online.pojos.requests_responses.SellTicketResponse;
import polp.online.singletons.RandomSingleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class TicketOffice {
    @Getter
    private @NonNull int availableTickets;
    private final Lock ticketLock = new ReentrantLock();

    public SellTicketResponse sellTicket(SellTicketRequest request) {
        // Simulate server response time
        try {
            Thread.sleep(RandomSingleton.getInstance().nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ticketLock.lock();

        if (availableTickets < 1) {
            ticketLock.unlock();
            return SellTicketResponse
                .builder()
                .type(SellTicketResponseType.SOLD_OUT)
                .build();
        }

        availableTickets--;
        ticketLock.unlock();

        Ticket ticket = new Ticket(request.getUuid());

        return new SellTicketResponse(SellTicketResponseType.SUCCESS, ticket);
    }

    public void addTickets(int numberOfTicketsToAdd) {
        availableTickets += numberOfTicketsToAdd;
    }
}
