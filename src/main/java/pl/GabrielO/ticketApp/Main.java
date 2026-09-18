package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.dao.EventDao;
import pl.GabrielO.ticketApp.dao.TicketPoolDao;
import pl.GabrielO.ticketApp.model.Event;
import pl.GabrielO.ticketApp.model.TicketPool;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventDao eventDao = new EventDao();
        TicketPoolDao ticketPoolDao = new TicketPoolDao();

        Event event = new Event();
        event.setName("Koncert rockowy");
        event.setEventDate(LocalDateTime.of(2026, 11, 20, 19, 0));
        eventDao.save(event);

        Long generatedEventId = event.getId();

        TicketPool standardPool = new TicketPool(null, 500, "STANDARD", generatedEventId, 0);
        TicketPool vipPool = new TicketPool(null, 50, "VIP", generatedEventId, 0);

        System.out.println("\nZapisywanie biletów do bazy...");
        ticketPoolDao.save(standardPool);
        ticketPoolDao.save(vipPool);

        System.out.println("\nPobieranie dostępnych biletów dla koncertu ID " + generatedEventId + ":");
        List<TicketPool> availablePools = ticketPoolDao.getPoolsForEvent(generatedEventId);

        for (TicketPool pool : availablePools) {
            System.out.println("- Typ: " + pool.getTicketType() + ", Dostępnych: " + pool.getAvailableTickets() + " (Wersja: " + pool.getVersion() + ")");
        }
    }
}