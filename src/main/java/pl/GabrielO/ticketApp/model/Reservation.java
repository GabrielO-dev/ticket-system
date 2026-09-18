package pl.GabrielO.ticketApp.model;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long eventId;
    private String customerName;
    private String ticketType;
    private LocalDateTime reservationTime;

    public Reservation() {}

    public Reservation(Long id, Long eventId, String ticketType, String customerName, LocalDateTime reservationTime) {
        this.id = id;
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.customerName = customerName;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
