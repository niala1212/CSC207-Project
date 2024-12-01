package app.gui.SearchByDepartureAirportFrames;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportController;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportViewModel;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportState;
import net.miginfocom.swing.MigLayout;

public class SearchByDepartureAirportFrame extends JFrame implements PropertyChangeListener {

    static final int SEARCHBYAIRPORT_WIDTH = 600;
    static final int SEARCHBYAIRPORT_HEIGHT = 600;
    static final String SEARCHBYAIRPORT_FONT = "Arial";

    private final SearchByDepartureAirportController searchByDepartureAirportController;
    private final SearchByDepartureAirportViewModel searchByDepartureAirportViewModel;

    private final String placeholderText = "Enter Departure Airport Code; eg: YYZ";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));
    private final JLabel airportNameLabel = new JLabel("", SwingConstants.CENTER);
    private final JScrollPane scrollPane = new JScrollPane();
    private final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    public SearchByDepartureAirportFrame(SearchByDepartureAirportViewModel searchByDepartureAirportViewModel,
                                         SearchByDepartureAirportController searchByDepartureAirportController) {
        this.searchByDepartureAirportViewModel = searchByDepartureAirportViewModel;
        this.searchByDepartureAirportViewModel.addPropertyChangeListener(this);
        this.searchByDepartureAirportController = searchByDepartureAirportController;

        addSearchBar();
        resultPanel.setLayout(new BorderLayout());
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Departure Airport");
        setSize(SEARCHBYAIRPORT_WIDTH, SEARCHBYAIRPORT_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        searchField.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.PLAIN, 20));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.PLAIN, 15));
        searchPanel.add(searchButton, "height 40, grow");

        // Search button action listener
        searchButton.addActionListener(event -> {
            String searchText = searchField.getText().trim();

            // Check if the search field contains the placeholder text
            if (searchText.isEmpty() || searchText.equals(placeholderText)) {
                // Display an error if the input is invalid
                displayError("Please enter a valid departure airport code.");
            } else {
                searchByDepartureAirportController.execute(searchText);
            }
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void displayResults(SearchByDepartureAirportState state) {
        resultPanel.removeAll();

        airportNameLabel.setText(state.getAirportName());
        airportNameLabel.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.BOLD, 24));
        resultPanel.add(airportNameLabel, BorderLayout.NORTH);

        JPanel flightListPanel = new JPanel(new GridLayout(0, 2, 10, 10));
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
        resultPanel.removeAll(); // Clear previous results or messages

        // Set the error message
        errorLabel.setText("<html><body style='text-align: center;'>" + errorMessage + "</body></html>");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.BOLD, 18));
        resultPanel.add(errorLabel, BorderLayout.CENTER);

        resultPanel.revalidate(); // Revalidate the panel to refresh the view
        resultPanel.repaint();
    }

    private void showFlightDetails(String flightNumber) {
        String flightDetails = searchByDepartureAirportViewModel.getState().getFlightDetailsString(flightNumber);
        if (flightDetails != null) {
            new DepartureAirportFlightDetailsFrame(searchByDepartureAirportViewModel.getState(), flightNumber);
        } else {
            JOptionPane.showMessageDialog(this, "Flight details not found for: " + flightNumber,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        SearchByDepartureAirportState state = searchByDepartureAirportViewModel.getState();
        if ("airportFlights".equals(event.getPropertyName())) {
            if (state.isSuccessful()) {
                displayResults(state);
            } else {
                displayError(state.getMessage());
            }
        }
        else if ("noFlightsError".equals(event.getPropertyName())) {
            displayError(state.getMessage());
        }
    }
}
