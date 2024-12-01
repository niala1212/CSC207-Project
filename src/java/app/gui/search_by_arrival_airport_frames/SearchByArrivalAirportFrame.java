package app.gui.search_by_arrival_airport_frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import adapters.search_by_arrival_airport.SearchByArrivalAirportController;
import adapters.search_by_arrival_airport.SearchByArrivalAirportState;
import adapters.search_by_arrival_airport.SearchByArrivalAirportViewModel;
import net.miginfocom.swing.MigLayout;

/**
 * The search box and result display for searching for flights by Arrival Airport.
 */
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings"})
public class SearchByArrivalAirportFrame extends JFrame implements PropertyChangeListener {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE_SEARCH = 20;
    private static final int FONT_SIZE_BUTTON = 15;
    private static final int FONT_SIZE_AIRPORT_NAME = 24;
    private static final int FONT_SIZE_ERROR = 18;

    private static final int GRID_LAYOUT_ROWS = 0;
    private static final int GRID_LAYOUT_COLS = 2;
    private static final int GRID_LAYOUT_HGAP = 10;
    private static final int GRID_LAYOUT_VGAP = 10;

    private final SearchByArrivalAirportViewModel searchByArrivalAirportViewModel;
    private final SearchByArrivalAirportController searchByArrivalAirportController;

    private final String placeholderText = "Enter Arrival Airport Code; eg: JFK";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));
    private final JLabel airportNameLabel = new JLabel("", SwingConstants.CENTER);
    private final JScrollPane scrollPane = new JScrollPane();
    private final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    public SearchByArrivalAirportFrame(SearchByArrivalAirportController searchByArrivalAirportController,
                                       SearchByArrivalAirportViewModel searchByArrivalAirportViewModel) {
        this.searchByArrivalAirportViewModel = searchByArrivalAirportViewModel;
        this.searchByArrivalAirportViewModel.addPropertyChangeListener(this);
        this.searchByArrivalAirportController = searchByArrivalAirportController;

        addSearchBar();
        resultPanel.setLayout(new BorderLayout());
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Arrival Airport");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        requestFocusInWindow();
    }

    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholderText)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholderText);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchPanel.add(searchField, "height 40, grow");
        searchField.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_SEARCH));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_BUTTON));
        searchPanel.add(searchButton, "height 40, grow");
        searchButton.addActionListener(event -> {
            searchByArrivalAirportController.execute(searchField.getText());
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void displayResults(SearchByArrivalAirportState state) {
        resultPanel.removeAll();

        airportNameLabel.setText(state.getAirportName());
        airportNameLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_AIRPORT_NAME));
        resultPanel.add(airportNameLabel, BorderLayout.NORTH);

        JPanel flightListPanel = new JPanel(new GridLayout(GRID_LAYOUT_ROWS, GRID_LAYOUT_COLS,
                GRID_LAYOUT_HGAP, GRID_LAYOUT_VGAP));
        for (String flightNumber : state.getFlightNumbers()) {
            JButton flightButton = new JButton(flightNumber);
            flightButton.addActionListener(event -> showFlightDetails(flightNumber));
            flightListPanel.add(flightButton);
        }

        scrollPane.setViewportView(flightListPanel);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void displayError(String errorMessage) {
        resultPanel.removeAll();

        errorLabel.setText("<html><body style='text-align: center;'>" + errorMessage + "</body></html>");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_ERROR));
        resultPanel.add(errorLabel, BorderLayout.CENTER);

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void showFlightDetails(String flightNumber) {
        String flightDetails = searchByArrivalAirportViewModel.getState().getFlightDetailsString(flightNumber);
        if (flightDetails != null) {
            new ArrivalAirportFlightDetailsFrame(searchByArrivalAirportViewModel.getState(), flightNumber);
        }
        else {
            JOptionPane.showMessageDialog(this, "Flight details not found for: " + flightNumber,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        SearchByArrivalAirportState state = searchByArrivalAirportViewModel.getState();
        if ("airportFlights".equals(event.getPropertyName())) {
            if (state.isSuccessful()) {
                displayResults(state);
            }
            else {
                displayError(state.getMessage());
            }
        }
    }
}
