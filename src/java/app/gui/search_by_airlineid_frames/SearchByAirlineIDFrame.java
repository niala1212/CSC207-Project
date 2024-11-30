package app.gui.search_by_airlineid_frames;

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

import adapters.search_by_airlineid.SearchByAirlineIDController;
import adapters.search_by_airlineid.SearchByAirlineIDState;
import adapters.search_by_airlineid.SearchByAirlineIDViewModel;
import net.miginfocom.swing.MigLayout;

/**
 * The search box and result display for searching for flights by Airline IATA code.
 */
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings"})
public class SearchByAirlineIDFrame extends JFrame implements PropertyChangeListener {
    // Constants for frame dimensions and font settings
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE_SEARCH = 20;
    private static final int FONT_SIZE_BUTTON = 15;
    private static final int FONT_SIZE_AIRLINE_NAME = 24;
    private static final int FONT_SIZE_ERROR = 18;

    private static final int GRID_LAYOUT_ROWS = 0;
    private static final int GRID_LAYOUT_COLS = 2;
    private static final int GRID_LAYOUT_HGAP = 10;
    private static final int GRID_LAYOUT_VGAP = 10;

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
     * @param searchByAirlineIDController controller for Airline ID
     * @param searchByAirlineIDViewModel viewModel for Airline ID
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
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        requestFocusInWindow();
    }

    /**
     * Adds the search bar with a text field and a search button.
     */
    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setForeground(Color.GRAY);
        searchField.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_SEARCH));

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

        searchPanel.add(searchField, "height 40, grow");

        // Create and add a search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_BUTTON));
        searchPanel.add(searchButton, "height 40, grow");
        // Bind button action to the controller's execute method
        searchButton.addActionListener(event -> searchByAirlineIDController.execute(searchField.getText()));

        add(searchPanel, BorderLayout.NORTH);
    }

    /**
     * Displays the results when a successful search occurs.
     * @param state state for the AirlineID
     */
    private void displayResults(SearchByAirlineIDState state) {
        // Clears any previous results
        resultPanel.removeAll();

        // Display the airline name
        airlineNameLabel.setText(state.getAirlineName());
        airlineNameLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_AIRLINE_NAME));
        resultPanel.add(airlineNameLabel, BorderLayout.NORTH);

        // Panel for flight numbers in a grid
        JPanel flightListPanel = new JPanel(new GridLayout(GRID_LAYOUT_ROWS, GRID_LAYOUT_COLS,
                GRID_LAYOUT_HGAP, GRID_LAYOUT_VGAP));
        for (String flightNumber : state.getFlightNumbers()) {
            // Create a button for each flight number
            JButton flightButton = new JButton(flightNumber);
            // Open details on click
            flightButton.addActionListener(event -> showFlightDetails(flightNumber));
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
     * @param errorMessage error message for when search fails
     */
    private void displayError(String errorMessage) {
        // Clears any previous results or errors
        resultPanel.removeAll();

        // Configure and display the error message
        errorLabel.setText("<html><body style='text-align: center;'>" + errorMessage + "</body></html>");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_ERROR));
        resultPanel.add(errorLabel, BorderLayout.CENTER);

        // Refresh the result panel
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    /**
     * Opens a new frame to show details of a specific flight.
     * @param flightNumber gives the flight number for a specific flight
     */
    private void showFlightDetails(String flightNumber) {
        String flightDetails = searchByAirlineIDViewModel.getState().getFlightDetailsString(flightNumber);
        if (flightDetails != null) {
            // Open the flight details frame if details are available
            new SearchByAirlineIDFlightDetailsFrame(searchByAirlineIDViewModel.getState(), flightNumber);
        }
        else {
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
            // Checks if the operation was successful or resulted in an error
            if (state.isSuccessful()) {
                displayResults(state);
            }
            else {
                displayError(state.getErrorMessage());
            }
        }
    }
}
