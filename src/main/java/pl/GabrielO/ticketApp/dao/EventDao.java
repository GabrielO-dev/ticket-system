package pl.GabrielO.ticketApp.dao;

import org.springframework.stereotype.Repository;
import pl.GabrielO.ticketApp.model.Event;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDao {

    private final DataSource dataSource;

    public EventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Event event) {
        String sql = "INSERT INTO events (name, event_date) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, event.getName());
            pstmt.setTimestamp(2, Timestamp.valueOf(event.getEventDate()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        event.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getLong("id"));
                event.setName(rs.getString("name"));
                event.setEventDate(rs.getTimestamp("event_date").toLocalDateTime());

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}