package app.gui.search_by_departure_airport_frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import adapters.search_by_departure_airport.SearchByDepartureAirportState;

/**
 * The frame for displaying the flight information for a specific flight in the departures view.
 */
public class DepartureAirportFlightDetailsFrame extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    public DepartureAirportFlightDetailsFrame(SearchByDepartureAirportState state, String flightNumber) {
        setTitle("Flight Details - " + flightNumber);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String flightDetails = state.getFlightDetailsString(flightNumber);
        JLabel flightDetailsLabel = new JLabel("<html><body style='text-align: center;'>"
                + flightDetails.replace("\n", "<br>") + "</body></html>");
        flightDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightDetailsLabel.setVerticalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(flightDetailsLabel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
