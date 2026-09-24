package pl.GabrielO.ticketApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookingRequest {

    private Long eventId;
    private String ticketType;

    @NotBlank(message = "Imię i nazwisko nie może być puste!")
    @Size(min = 3, message = "Wpisz co najmniej 3 znaki.")
    private String customerName;

    public BookingRequest() {}

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
