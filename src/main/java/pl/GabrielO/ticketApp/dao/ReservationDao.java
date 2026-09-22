package pl.GabrielO.ticketApp.dao;

import org.springframework.stereotype.Repository;
import pl.GabrielO.ticketApp.model.Reservation;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {

    private final DataSource dataSource;

    public ReservationDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservations (event_id, customer_name, ticket_type, reservation_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getReservationsForEvent(Long eventId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE event_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, eventId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getLong("id"));
                    reservation.setEventId(rs.getLong("event_id"));
                    reservation.setCustomerName(rs.getString("customer_name"));
                    reservation.setTicketType(rs.getString("ticket_type"));
                    reservation.setReservationTime(rs.getTimestamp("reservation_time").toLocalDateTime());

                    reservations.add(reservation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}