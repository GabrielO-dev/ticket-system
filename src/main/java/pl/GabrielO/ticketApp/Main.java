package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.exception.TicketBookingException;
import pl.GabrielO.ticketApp.service.TicketBookingService;

public class Main {
    public static void main(String[] args) {
        TicketBookingService ticketBookingService = new TicketBookingService();

        Long eventId = 2L;

        try {
            System.out.println("Test 1: Prawidłowy zakup...");
            ticketBookingService.bookTicket(eventId, "VIP", "Anna Nowak");

            System.out.println("Test 2: Próba zakupu nieistniejącego typu biletu...");
            ticketBookingService.bookTicket(eventId, "PREMIUM", "Tomasz Kot");

            System.out.println("To się nie powinno wyświetlić.");

        } catch (TicketBookingException e) {
            System.err.println("PRZECHWYCONO BŁĄD BIZNESOWY: " + e.getMessage());
        }
    }
}