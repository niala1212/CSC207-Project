package app.gui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByAirlineID.SearchByAirlineIDState;
import entities.Flight;
import net.miginfocom.swing.MigLayout;

public class SearchByAirlineIDFrame extends JFrame implements PropertyChangeListener {
    static final int SEARCHBYAIRLINEID_WIDTH = 600;
    static final int SEARCHBYAIRLINEID_HEIGHT = 600;
    static final String SEARCHBYAIRLINEID_FONT = "Arial";

    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel;
    private final SearchByAirlineIDController searchByAirlineIDController;

    private final String placeholderText = "Enter Airline IATA Code; eg: AA";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));
    private final JLabel airlineNameLabel = new JLabel("", SwingConstants.CENTER);
    private final JScrollPane scrollPane = new JScrollPane();
    private final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    public SearchByAirlineIDFrame(SearchByAirlineIDController searchByAirlineIDController,
                                  SearchByAirlineIDViewModel searchByAirlineIDViewModel) {
        this.searchByAirlineIDViewModel = searchByAirlineIDViewModel;
        this.searchByAirlineIDViewModel.addPropertyChangeListener(this);
        this.searchByAirlineIDController = searchByAirlineIDController;

        addSearchBar();
        resultPanel.setLayout(new BorderLayout());
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Airline ID");
        setSize(SEARCHBYAIRLINEID_WIDTH, SEARCHBYAIRLINEID_HEIGHT);
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
        searchField.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 20));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.PLAIN, 15));
        searchPanel.add(searchButton, "height 40, grow");
        searchButton.addActionListener(event -> {
            searchByAirlineIDController.execute(searchField.getText());
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void displayResults(SearchByAirlineIDState state) {
        resultPanel.removeAll();

        airlineNameLabel.setText(state.getAirlineName());
        airlineNameLabel.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.BOLD, 24));
        resultPanel.add(airlineNameLabel, BorderLayout.NORTH);

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
        resultPanel.removeAll();

        errorLabel.setText("<html><body style='text-align: center;'>" + errorMessage + "</body></html>");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(SEARCHBYAIRLINEID_FONT, Font.BOLD, 18));
        resultPanel.add(errorLabel, BorderLayout.CENTER);

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void showFlightDetails(String flightNumber) {
        for (Flight flight : searchByAirlineIDViewModel.getState().getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                new FlightDetailsFrame(flight);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Flight details not found for: " + flightNumber,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        SearchByAirlineIDState state = searchByAirlineIDViewModel.getState();
        if ("airlineFlights".equals(event.getPropertyName())) {
            if (state.isSuccessful()) {
                displayResults(state);
            } else {
                displayError(state.getErrorMessage());
            }
        }
    }
}
