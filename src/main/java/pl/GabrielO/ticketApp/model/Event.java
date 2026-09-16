package pl.GabrielO.ticketApp.model;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String name;
    private LocalDateTime eventDate;

    public Event(Long id, String name, LocalDateTime eventDate) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
