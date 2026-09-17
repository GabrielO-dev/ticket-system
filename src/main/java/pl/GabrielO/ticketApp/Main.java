package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.dao.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Uruchamianie systemu biletowego...");

        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            System.out.println("Test połączenia: SUKCES! Baza jest gotowa do działania.");
        } else {
            System.out.println("Test połączenia: BŁĄD!");
        }
    }
}