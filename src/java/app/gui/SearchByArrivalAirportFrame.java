package app.gui;

import java.util.List;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entities.Flight;
import adapters.SearchByArrivalAirport.SearchByArrivalAirportController;
import adapters.SearchByArrivalAirport.SearchByArrivalAirportViewModel;
import adapters.SearchByArrivalAirport.SearchByArrivalAirportState;
import net.miginfocom.swing.MigLayout;

/**
 * The searchbox to search up flights by airport.
 */
public class SearchByArrivalAirportFrame extends JFrame implements PropertyChangeListener {
    static final int SEARCHBYAIRPORT_WIDTH = 600;
    static final int SEARCHBYAIRPORT_HEIGHT = 600;
    static final String SEARCHBYAIRPORT_FONT = "Arial";

    private final SearchByArrivalAirportViewModel searchByArrivalAirportViewModel;
    private final SearchByArrivalAirportController searchByArrivalAirportController;

    // search panel
    private final String placeholderText = "Enter Airport IATA Code";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));

    // result panel
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));

    public SearchByArrivalAirportFrame(SearchByArrivalAirportController searchByArrivalAirportController,
                                       SearchByArrivalAirportViewModel searchByArrivalAirportViewModel) throws HeadlessException {
        this.searchByArrivalAirportViewModel = searchByArrivalAirportViewModel;
        this.searchByArrivalAirportViewModel.addPropertyChangeListener(this);
        this.searchByArrivalAirportController = searchByArrivalAirportController;

        addSearchBar();
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Airport");
        setSize(SEARCHBYAIRPORT_WIDTH, SEARCHBYAIRPORT_HEIGHT);
        setVisible(true);
        // Request focus for the frame itself, not the text field
        requestFocusInWindow();
    }

    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setColumns(1000000);
        // Set initial color to gray for placeholder text
        searchField.setForeground(Color.GRAY);
        // Add FocusListener to manage the placeholder text
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the placeholder when the field is focused
                if (searchField.getText().equals(placeholderText)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder text if the field is empty
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholderText);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchPanel.add(searchField, "height 40, grow");
        searchField.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.PLAIN, 20));

        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton, "height 40, grow");
        searchButton.addActionListener(event -> {
            String airportCode = searchField.getText();
            if (airportCode != null && !airportCode.trim().isEmpty()) {
                // Assuming the airport is a departure airport (set to true for testing)
                searchByArrivalAirportController.execute(airportCode.trim());
            }
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void addTable(SearchByArrivalAirportState searchByAirportState) {
        if (searchByAirportState.getMessage() != null) {
            resultPanel.add(new JLabel(searchByAirportState.getMessage()), "grow");
        } else {
            String[] columnNames = {"Flight Number", "Departure Time", "Arrival Time", "Status"};
            List<String> flightNumbers = searchByAirportState.getFlightNumbers();
            List<Flight> flights = searchByAirportState.getFlights();

            // Populate table with flight data
            Object[][] data = new Object[flights.size()][4];
            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                data[i][0] = flightNumbers.get(i);
                data[i][1] = flight.getScheduledDepartureTime();
                data[i][2] = flight.getScheduledArrivalTime();
                data[i][3] = flight.getStatus();
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            resultPanel.add(scrollPane, "grow");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("airportSearchResults".equals(event.getPropertyName())) {
            addTable(searchByArrivalAirportViewModel.getState());
        }
    }
}
