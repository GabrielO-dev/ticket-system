package pl.GabrielO.ticketApp.service;

import org.springframework.stereotype.Service;
import pl.GabrielO.ticketApp.dao.ReservationDao;
import pl.GabrielO.ticketApp.dao.TicketPoolDao;
import pl.GabrielO.ticketApp.exception.TicketBookingException;
import pl.GabrielO.ticketApp.model.Reservation;
import pl.GabrielO.ticketApp.model.TicketPool;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketBookingService {

    private final TicketPoolDao ticketPoolDao;
    private final ReservationDao reservationDao;

    public TicketBookingService(TicketPoolDao ticketPoolDao, ReservationDao reservationDao) {
        this.ticketPoolDao = ticketPoolDao;
        this.reservationDao = reservationDao;
    }

    public void bookTicket(Long eventId, String ticketType, String customerName) {
        List<TicketPool> pools = ticketPoolDao.getPoolsForEvent(eventId);

        TicketPool targetPool = null;
        for (TicketPool pool : pools) {
            if (pool.getTicketType().equalsIgnoreCase(ticketType)) {
                targetPool = pool;
                break;
            }
        }

        if (targetPool == null) {
            throw new TicketBookingException("Nie znaleziono puli biletów typu: " + ticketType);
        }

        if (targetPool.getAvailableTickets() <= 0) {
            throw new TicketBookingException("Brak dostępnych biletów w puli " + ticketType + "! Wyprzedane.");
        }

        targetPool.setAvailableTickets(targetPool.getAvailableTickets() - 1);
        boolean isUpdated = ticketPoolDao.updatePool(targetPool);

        if (!isUpdated) {
            throw new TicketBookingException("Błąd współbieżności! Ktoś inny właśnie kupił ten bilet. Spróbuj ponownie.");
        }

        Reservation reservation = new Reservation();
        reservation.setEventId(eventId);
        reservation.setCustomerName(customerName);
        reservation.setTicketType(ticketType);
        reservation.setReservationTime(LocalDateTime.now());

        reservationDao.save(reservation);
    }
}