package pl.GabrielO.ticketApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.GabrielO.ticketApp.dto.BookingRequest;
import pl.GabrielO.ticketApp.exception.TicketBookingException;
import pl.GabrielO.ticketApp.service.TicketBookingService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketBookingService bookingService;

    public TicketController(TicketBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookingRequest request) {
        try {
            bookingService.bookTicket(request.getEventId(), request.getTicketType(), request.getCustomerName());
            return ResponseEntity.ok("SUKCES: Zarezerwowano bilet " + request.getTicketType() + " dla " + request.getCustomerName());
        } catch (TicketBookingException e) {
            return ResponseEntity.badRequest().body("BŁĄD: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Błąd serwera: " + e.getMessage());
        }
    }
}