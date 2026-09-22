package pl.GabrielO.ticketApp;

import pl.GabrielO.ticketApp.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater( () -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);

        });
    }
}