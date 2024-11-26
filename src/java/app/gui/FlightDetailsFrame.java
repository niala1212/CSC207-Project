package app.gui;

import javax.swing.*;
import java.awt.*;
import entities.Flight;

public class FlightDetailsFrame extends JFrame {

    public FlightDetailsFrame(Flight flight) {
        setTitle("Flight Details - " + flight.getFlightNumber());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel flightDetailsLabel = new JLabel("<html><body style='text-align: center;'>" +
                flight.toString().replace("\n", "<br>") + "</body></html>");
        flightDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightDetailsLabel.setVerticalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(flightDetailsLabel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}

//package app.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import entities.Flight;
//
//public class FlightDetailsFrame extends JFrame {
//
//    public FlightDetailsFrame(Flight flight) {
//        setTitle("Flight Details - " + flight.getFlightNumber());
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Extract the first line (header) and the rest of the flight details
//        String flightNumberHeader = "Flight Information:";
//        String flightDetails = flight.toString().replaceFirst("Flight Information:\n", ""); // Remove the header from toString output
//
//        // Create a centered label for the first line (flight number)
//        JLabel flightNumberLabel = new JLabel("<html><body style='text-align: center;'>" + flightNumberHeader + "</body></html>");
//        flightNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        flightNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
//        add(flightNumberLabel, BorderLayout.NORTH);
//
//        // Left-aligned text for the rest of the flight details
//        JTextArea flightDetailsTextArea = new JTextArea();
//        flightDetailsTextArea.setText(flightDetails);
//        flightDetailsTextArea.setWrapStyleWord(true);
//        flightDetailsTextArea.setLineWrap(true);
//        flightDetailsTextArea.setEditable(false);
//        flightDetailsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        JScrollPane scrollPane = new JScrollPane(flightDetailsTextArea);
//        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        add(scrollPane, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//}
