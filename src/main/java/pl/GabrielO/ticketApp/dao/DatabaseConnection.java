package pl.GabrielO.ticketApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:./ticket_system_db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {

            boolean shouldReconnect = false;

            if (connection == null) {
                shouldReconnect = true;
            } else if (connection.isClosed()) {
                shouldReconnect = true;
            }

            if (shouldReconnect) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Pomyślnie połączono z bazą danych H2.");

                initializeDatabase();
            }
        } catch (SQLException e) {
            System.err.println("Błąd: Problem z połączeniem z bazą dancyh.");
            e.printStackTrace();
        }

        return connection;
    }

    private static void initializeDatabase() {
        try (Statement statement = connection.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")));
            statement.execute(sql);
            System.out.println("Struktura bazy danych została zainicjalizowana.");
        } catch (Exception e) {
            System.err.println("Błąd podczas inicjalizacji bazy danych: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Połączenie z bazą zostało zamknięte.");
            } catch (SQLException e) {
                System.err.println("Błąd podczas zamykania połączenia!");
                e.printStackTrace();
            }
        }
    }
}
