package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.dao.ReservationDao;
import pl.GabrielO.ticketApp.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReservationDao reservationDao = new ReservationDao();

        System.out.println("Tworzenie nowej rezerwacji...");

        Reservation newReservation = new Reservation();
        newReservation.setEventId(2L);
        newReservation.setCustomerName("Jan Kowalski");
        newReservation.setTicketType("VIP");
        newReservation.setReservationTime(LocalDateTime.now());

        reservationDao.save(newReservation);

        System.out.println("\nLista rezerwacji dla tego koncertu:");
        List<Reservation> reservations = reservationDao.getReservationsForEvent(2L);
        for (Reservation r : reservations) {
            System.out.println("- Klient: " + r.getCustomerName() + " | Typ: " + r.getTicketType() + " | Data kupna: " + r.getReservationTime());
        }
    }
}