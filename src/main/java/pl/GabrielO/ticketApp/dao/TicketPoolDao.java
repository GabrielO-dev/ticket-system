package pl.GabrielO.ticketApp.dao;

import pl.GabrielO.ticketApp.model.TicketPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketPoolDao {

    public void save(TicketPool pool) {
        String sql = "INSERT INTO ticket_pools (event_id, ticket_type, available_tickets, version) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, pool.getEventId());
            pstmt.setString(2, pool.getTicketType());
            pstmt.setInt(3, pool.getAvailableTickets());
            pstmt.setInt(4, pool.getVersion());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pool.setId(generatedKeys.getLong(1));
                    }
                }

                System.out.println("Utworzono pulę biletów: " + pool.getTicketType() +
                        " (" + pool.getAvailableTickets() + " sztuk) dla wydarzenia ID: " + pool.getEventId());
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas zapisywania puli biletów do bazy!");
            e.printStackTrace();
        }
    }

    public List<TicketPool> getPoolsForEvent(Long eventId) {
        List<TicketPool> pools = new ArrayList<>();
        String sql = "SELECT * FROM ticket_pools WHERE event_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, eventId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TicketPool pool = new TicketPool();
                    pool.setId(rs.getLong("id"));
                    pool.setEventId(rs.getLong("event_id"));
                    pool.setTicketType(rs.getString("ticket_type"));
                    pool.setAvailableTickets(rs.getInt("available_tickets"));
                    pool.setVersion(rs.getInt("version"));

                    pools.add(pool);
                }
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania puli biletów!");
            e.printStackTrace();
        }

        return pools;
    }
}
