package app.gui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByAirlineID.SearchByAirlineIDState;
import entities.Flight;
import net.miginfocom.swing.MigLayout;

/**
 * The frame that handles the search for flights by Airline ID (IATA code).
 */
public class SearchByAirlineIDFrame extends JFrame implements PropertyChangeListener {
    static final int SEARCHBYAIRLINEID_WIDTH = 600;
    static final int SEARCHBYAIRLINEID_HEIGHT = 600;
    static final String SEARCHBYAIRLINEID_FONT = "Arial";

    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel;
    private final SearchByAirlineIDController searchByAirlineIDController;

    private final String placeholderText = "Enter Airline IATA Code; eg: AA";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));

    public SearchByAirlineIDFrame(SearchByAirlineIDController searchByAirlineIDController,
                                  SearchByAirlineIDViewModel searchByAirlineIDViewModel) throws HeadlessException {
        this.searchByAirlineIDViewModel = searchByAirlineIDViewModel;
        this.searchByAirlineIDViewModel.addPropertyChangeListener(this);
        this.searchByAirlineIDController = searchByAirlineIDController;

        addSearchBar();
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Airline ID");
        setSize(SEARCHBYAIRLINEID_WIDTH, SEARCHBYAIRLINEID_HEIGHT);
        setVisible(true);
        requestFocusInWindow();
    }

    // Add the search bar where the user can enter the IATA code
    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setColumns(1000000);
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
        searchField.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 20));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 15));
        searchPanel.add(searchButton, "height 40, grow");
        searchButton.addActionListener(event -> {
            searchByAirlineIDController.execute(searchField.getText());
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    // Method to add a table displaying the airline name and list of flight numbers
    private void addFlightsTable(SearchByAirlineIDState state) {
        String[] columnNames = {"IATA Flight Number"};
        Object[][] data = new Object[state.getFlightNumbers().size()][1];

        for (int i = 0; i < state.getFlightNumbers().size(); i++) {
            data[i][0] = state.getFlightNumbers().get(i);
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        resultPanel.add(scrollPane, "grow");

        // Add listener for table row clicks
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNumber = (String) table.getValueAt(selectedRow, 0);
                    showFlightDetails(flightNumber);
                }
            }
        });

        JLabel airlineNameLabel = new JLabel("Airline: " + state.getAirportName());
        resultPanel.add(airlineNameLabel, "wrap");
    }

    // Method to show detailed flight info in a new window
    private void showFlightDetails(String flightNumber) {
        // Open a new window with detailed flight information
        // Retrieve the full flight details using the ViewModel
        SearchByAirlineIDState state = searchByAirlineIDViewModel.getState();
        for (Flight flight : state.getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                new FlightDetailsFrame(flight);  // Open a new window to display flight details
                break;
            }
        }
    }

    // Method to handle property changes and update the UI
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("flightDetails".equals(event.getPropertyName())) {
            addFlightsTable(searchByAirlineIDViewModel.getState());
        } else if ("error".equals(event.getPropertyName())) {
            resultPanel.add(new JLabel("Error: " + event.getNewValue()), "grow");
        }
    }
}