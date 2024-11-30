package app.gui.SearchByAirlineIDFrames;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import adapters.search_by_airlineid.SearchByAirlineIDController;
import adapters.search_by_airlineid.SearchByAirlineIDViewModel;
import adapters.search_by_airlineid.SearchByAirlineIDState;
import net.miginfocom.swing.MigLayout;

/**
 * The search box and result display for searching for flights by Airline IATA code.
 */
public class SearchByAirlineIDFrame extends JFrame implements PropertyChangeListener {
    // Constants for frame dimensions and font settings
    static final int SEARCHBYAIRLINEID_WIDTH = 600;
    static final int SEARCHBYAIRLINEID_HEIGHT = 600;
    static final String SEARCHBYAIRLINEID_FONT = "Arial";

    // ViewModel to observe and reflect UI changes
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel;
    // Controller to handle user input and execute the use case
    private final SearchByAirlineIDController searchByAirlineIDController;

    // Placeholder text for the search bar
    private final String placeholderText = "Enter Airline IATA Code; eg: 'AC' for Air Canada";
    // Panels for search input and result display
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));
    // Label to display the airline name
    private final JLabel airlineNameLabel = new JLabel("", SwingConstants.CENTER);
    // Scroll pane for displaying flight list
    private final JScrollPane scrollPane = new JScrollPane();
    // Label for displaying error messages
    private final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    /**
     * Constructor for the frame. Sets up the UI and binds listeners to the ViewModel.
     */
    public SearchByAirlineIDFrame(SearchByAirlineIDController searchByAirlineIDController,
                                  SearchByAirlineIDViewModel searchByAirlineIDViewModel) {
        // Initialize controller and ViewModel
        this.searchByAirlineIDViewModel = searchByAirlineIDViewModel;
        this.searchByAirlineIDViewModel.addPropertyChangeListener(this);
        this.searchByAirlineIDController = searchByAirlineIDController;

        // Add the search bar to the UI
        addSearchBar();
        resultPanel.setLayout(new BorderLayout());
        add(resultPanel, BorderLayout.CENTER);

        // Configure frame settings
        setTitle("Flight Tracker Search By Airline ID");
        setSize(SEARCHBYAIRLINEID_WIDTH, SEARCHBYAIRLINEID_HEIGHT);
        setVisible(true);
        requestFocusInWindow();
    }

    /**
     * Adds the search bar with a text field and a search button.
     */
    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setForeground(Color.GRAY);
        // Add focus listeners for placeholder behavior
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear placeholder text when focused
                if (searchField.getText().equals(placeholderText)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder text if empty when focus is lost
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholderText);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        // Configure and add the search field to the panel
        searchPanel.add(searchField, "height 40, grow");
        searchField.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 20));

        // Create and add a search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 15));
        searchPanel.add(searchButton, "height 40, grow");
        // Bind button action to the controller's execute method
        searchButton.addActionListener(event -> searchByAirlineIDController.execute(searchField.getText()));

        add(searchPanel, BorderLayout.NORTH);
    }

    /**
     * Displays the results when a successful search occurs.
     */
    private void displayResults(SearchByAirlineIDState state) {
        resultPanel.removeAll(); // Clear any previous results

        // Display the airline name
        airlineNameLabel.setText(state.getAirlineName());
        airlineNameLabel.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.BOLD, 24));
        resultPanel.add(airlineNameLabel, BorderLayout.NORTH);

        // Panel for flight numbers in a grid
        JPanel flightListPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        for (String flightNumber : state.getFlightNumbers()) {
            // Create a button for each flight number
            JButton flightButton = new JButton(flightNumber);
            flightButton.addActionListener(event -> showFlightDetails(flightNumber)); // Open details on click
            flightListPanel.add(flightButton);
        }

        // Add the flight list to the scroll pane
        scrollPane.setViewportView(flightListPanel);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Refresh the result panel
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    /**
     * Displays an error message when the search fails.
     */
    private void displayError(String errorMessage) {
        resultPanel.removeAll(); // Clear any previous results or errors

        // Configure and display the error message
        errorLabel.setText("<html><body style='text-align: center;'>" + errorMessage + "</body></html>");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.BOLD, 18));
        resultPanel.add(errorLabel, BorderLayout.CENTER);

        // Refresh the result panel
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    /**
     * Opens a new frame to show details of a specific flight.
     */
    private void showFlightDetails(String flightNumber) {
        String flightDetails = searchByAirlineIDViewModel.getState().getFlightDetailsString(flightNumber);
        if (flightDetails != null) {
            // Open the flight details frame if details are available
            new SearchByAirlineIDFlightDetailsFrame(searchByAirlineIDViewModel.getState(), flightNumber);
        } else {
            // Show an error dialog if details are not found
            JOptionPane.showMessageDialog(this, "Flight details not found for: " + flightNumber,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Responds to property changes in the ViewModel's state.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        SearchByAirlineIDState state = searchByAirlineIDViewModel.getState();
        if ("airlineFlights".equals(event.getPropertyName())) {
            // Check if the operation was successful or resulted in an error
            if (state.isSuccessful()) {
                displayResults(state);
            } else {
                displayError(state.getErrorMessage());
            }
        }
    }
}
