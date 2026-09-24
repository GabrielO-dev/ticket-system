package pl.GabrielO.ticketApp.dao;

import org.springframework.stereotype.Repository;
import pl.GabrielO.ticketApp.model.TicketPool;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketPoolDao {

    private final DataSource dataSource;

    public TicketPoolDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(TicketPool pool) {
        String sql = "INSERT INTO ticket_pools (event_id, ticket_type, available_tickets, version) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TicketPool> getPoolsForEvent(Long eventId) {
        List<TicketPool> pools = new ArrayList<>();
        String sql = "SELECT * FROM ticket_pools WHERE event_id = ?";

        try (Connection conn = dataSource.getConnection();
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
                    pool.setPrice(rs.getBigDecimal("price"));

                    pools.add(pool);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pools;
    }

    public boolean updatePool(TicketPool pool) {
        String sql = "UPDATE ticket_pools SET available_tickets = ?, version = version + 1 WHERE id = ? AND version = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pool.getAvailableTickets());
            pstmt.setLong(2, pool.getId());
            pstmt.setInt(3, pool.getVersion());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                pool.setVersion(pool.getVersion() + 1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}