package pl.GabrielO.ticketApp.dao;

import pl.GabrielO.ticketApp.model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservations (event_id, customer_name, ticket_type, reservation_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, reservation.getEventId());
            pstmt.setString(2, reservation.getCustomerName());
            pstmt.setString(3, reservation.getTicketType());
            pstmt.setTimestamp(4, Timestamp.valueOf(reservation.getReservationTime()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reservation.setId(generatedKeys.getLong(1));
                    }
                }
                System.out.println("Sukces: Zapisano rezerwację dla klienta '" + reservation.getCustomerName() +
                        "' (Bilet: " + reservation.getTicketType() + ")");
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas zapisywania rezerwacji!");
            e.printStackTrace();
        }
    }

    public List<Reservation> getReservationsForEvent(Long eventId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE event_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, eventId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Reservation res = new Reservation();

                    res.setId(rs.getLong("id"));
                    res.setEventId(rs.getLong("event_id"));
                    res.setCustomerName(rs.getString("customer_name"));
                    res.setTicketType(rs.getString("ticket_type"));
                    res.setReservationTime(rs.getTimestamp("reservation_time").toLocalDateTime());

                    reservations.add(res);
                }
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania list rezerwacji!");
            e.printStackTrace();
        }

        return reservations;
    }
}
