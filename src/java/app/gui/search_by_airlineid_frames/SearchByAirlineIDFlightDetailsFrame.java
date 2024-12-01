package app.gui.search_by_airlineid_frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import adapters.search_by_airlineid.SearchByAirlineIDState;

/**
 * The window displaying flight details for a selected flight form the Search By Airline ID window.
 */
public class SearchByAirlineIDFlightDetailsFrame extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    public SearchByAirlineIDFlightDetailsFrame(SearchByAirlineIDState state, String flightNumber) {
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

