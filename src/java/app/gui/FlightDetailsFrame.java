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

        // Display all flight details using the toString method
        JTextArea flightDetailsTextArea = new JTextArea(10, 30);
        flightDetailsTextArea.setText(flight.toString());
        flightDetailsTextArea.setWrapStyleWord(true);
        flightDetailsTextArea.setLineWrap(true);
        flightDetailsTextArea.setEditable(false);  // Make it non-editable
        JScrollPane scrollPane = new JScrollPane(flightDetailsTextArea);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
