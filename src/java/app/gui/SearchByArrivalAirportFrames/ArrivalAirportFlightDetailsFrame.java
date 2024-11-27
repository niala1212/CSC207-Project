package app.gui.SearchByArrivalAirportFrames;

import javax.swing.*;
import java.awt.*;
import adapters.SearchByArrivalAirport.SearchByArrivalAirportState;

public class ArrivalAirportFlightDetailsFrame extends JFrame {

    public ArrivalAirportFlightDetailsFrame(SearchByArrivalAirportState state, String flightNumber) {
        setTitle("Flight Details - " + flightNumber);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String flightDetails = state.getFlightDetailsString(flightNumber);
        JLabel flightDetailsLabel = new JLabel("<html><body style='text-align: center;'>" +
                flightDetails.replace("\n", "<br>") + "</body></html>");
        flightDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightDetailsLabel.setVerticalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(flightDetailsLabel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}