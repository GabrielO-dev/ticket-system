package pl.GabrielO.ticketApp.model;

import java.math.BigDecimal;

public class TicketPool {
    private Long id;
    private Long eventId;
    private String ticketType;
    private int availableTickets;
    private int version;
    private BigDecimal price;

    public TicketPool() {

    }

    public TicketPool(Long id, int availableTickets, String ticketType, Long eventId, int version) {
        this.id = id;
        this.availableTickets = availableTickets;
        this.ticketType = ticketType;
        this.eventId = eventId;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getVersion() {
        return version;
    }

    public BigDecimal getPrice() {return price;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPrice(BigDecimal price) {this.price = price;}
}
