package app.gui.SearchByAirlineIDFrames;

import javax.swing.*;
import java.awt.*;
import adapters.search_by_airlineid.SearchByAirlineIDState;

/**
 * The window displaying flight details for a selected flight form the Sear By Airline ID window.
 */
public class SearchByAirlineIDFlightDetailsFrame extends JFrame {

    public SearchByAirlineIDFlightDetailsFrame(SearchByAirlineIDState state, String flightNumber) {
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

