package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.dao.EventDao;
import pl.GabrielO.ticketApp.model.Event;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
        public static void main(String[] args) {
            System.out.println("Uruchamianie systemu biletowego...");

            EventDao eventDao = new EventDao();

            Event rockConcert = new Event();
            rockConcert.setName("Koncert Rockowy - Warsaw Arena");
            rockConcert.setEventDate(LocalDateTime.of(2026, 10, 15, 20, 0));

            System.out.println("Próba zapisu do bazy...");
            eventDao.save(rockConcert);

            System.out.println("\nLista wydarzeń w bazie:");
            List<Event> allEvents = eventDao.findAll();
            for (Event e : allEvents) {
                System.out.println(e.getId() + " | " + e.getName() + " | " + e.getEventDate());
            }
        }
}