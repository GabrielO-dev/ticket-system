package pl.GabrielO.ticketApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.GabrielO.ticketApp.dao.EventDao;
import pl.GabrielO.ticketApp.dao.TicketPoolDao;
import pl.GabrielO.ticketApp.dto.BookingRequest;
import pl.GabrielO.ticketApp.service.TicketBookingService;

import java.math.BigDecimal;

@Controller
public class WebController {

    private final EventDao eventDao;
    private final TicketPoolDao ticketPoolDao;
    private final TicketBookingService bookingService;

    public WebController(EventDao eventDao, TicketPoolDao ticketPoolDao, TicketBookingService bookingService) {
        this.eventDao = eventDao;
        this.ticketPoolDao = ticketPoolDao;
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("events", eventDao.findAll());
        return "index";
    }

    @GetMapping("/book")
    public String showBookingForm(@RequestParam Long eventId, Model model) {
        BookingRequest request = new BookingRequest();
        request.setEventId(eventId);
        model.addAttribute("bookingRequest", request);

        model.addAttribute("availablePools", ticketPoolDao.getPoolsForEvent(eventId));

        return "book";
    }

    @PostMapping("/book")
    public String processBooking(@ModelAttribute BookingRequest bookingRequest, Model model) {
        try {
            BigDecimal price = bookingService.bookTicket(bookingRequest.getEventId(), bookingRequest.getTicketType(), bookingRequest.getCustomerName());
            model.addAttribute("message", "✅ SUKCES: Zarezerwowano bilet " + bookingRequest.getTicketType() + " dla " + bookingRequest.getCustomerName() + ". Do zapłaty: " + price + " PLN.");
        } catch (Exception e) {
            model.addAttribute("message", "❌ BŁĄD: " + e.getMessage());
        }
        return "result";
    }
}