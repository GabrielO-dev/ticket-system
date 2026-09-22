package pl.GabrielO.ticketApp.ui;

import pl.GabrielO.ticketApp.exception.TicketBookingException;
import pl.GabrielO.ticketApp.service.TicketBookingService;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final TicketBookingService bookingService;
    private final Long currentEventId = 2L;

    public MainWindow() {
        bookingService = new TicketBookingService();

        setTitle("System Rezerwacji Biletów");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BorderLayout(10, 10));
        mainPanel.setBorder( BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Kup bilet na koncert", SwingConstants.CENTER);
        titleLabel.setFont( new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout( new GridLayout(3, 2, 10, 10));

        formPanel.add( new JLabel("Imię i nazwisko:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add( new JLabel("Typ biletu:"));
        String[] ticketTypes = {"STANDARD", "PREMIUM", "VIP"};
        JComboBox<String> ticketBox = new JComboBox<>(ticketTypes);
        formPanel.add(ticketBox);

        formPanel.add( new Label(""));
        JButton bookButton = new JButton("Zarezerwuj bilet");
        bookButton.setFont( new Font("Arial", Font.BOLD, 14));
        bookButton.setBackground( new Color(46, 204, 113));
        bookButton.setOpaque(true);
        bookButton.setBorderPainted(false);
        formPanel.add(bookButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JTextArea statusArea = new JTextArea(6, 30);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        bookButton.addActionListener( e -> {
            String customerName = nameField.getText().trim();
            String ticketType = (String) ticketBox.getSelectedItem();

            if (customerName.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                "Proszę podać imię i nazwisko!", "Błąd wprowadzania", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                statusArea.append("Wysyłanie zapytania dla: " + customerName + " (" + ticketType + ")...\n");

                bookingService.bookTicket(currentEventId, ticketType, customerName);

                statusArea.append("✅ SUKCES: Bilet został zakupiony!\n\n");
                nameField.setText("");

            } catch (TicketBookingException ex) {
                statusArea.append("❌ BŁĄD: " + ex.getMessage() + "\n\n");
                JOptionPane.showMessageDialog(this,
                ex.getMessage(), "Odmowa rezerwacji", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(mainPanel);
    }
}
