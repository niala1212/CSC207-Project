package app.gui;

import adapters.AbstractState;
import adapters.SearchAirportLanded.SearchAirportLandedController;
import adapters.SearchAirportLanded.SearchAirportLandedViewModel;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportController;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportViewModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchByAirportFrame extends JFrame implements PropertyChangeListener {
    static final int SEARCHBYAIRPORT_WIDTH = 600;
    static final int SEARCHBYAIRPORT_HEIGHT = 600;
    static final String SEARCHBYAIRPORT_FONT = "Arial";

    private final SearchByDepartureAirportViewModel searchByDepartureAirportViewModel;
    private final SearchByDepartureAirportController searchByDepartureAirportController;
    private final SearchAirportLandedViewModel searchAirportLandedViewModel;
    private final SearchAirportLandedController searchAirportLandedController;

    // search panel
    private final String placeholderText = "Enter Airport Code (IATA code)";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));

    // result panel
    private final JLabel errorLabel = new JLabel();
    private final JScrollPane scrollPane = new JScrollPane();
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));

    public SearchByAirportFrame(SearchByDepartureAirportController searchByDepartureAirportController,
                                SearchByDepartureAirportViewModel searchByDepartureAirportViewModel,
                                SearchAirportLandedController searchAirportLandedController,
                                SearchAirportLandedViewModel searchAirportLandedViewModel) throws HeadlessException {
        this.searchByDepartureAirportViewModel = searchByDepartureAirportViewModel;
        this.searchByDepartureAirportController = searchByDepartureAirportController;
        this.searchAirportLandedViewModel = searchAirportLandedViewModel;
        this.searchAirportLandedController = searchAirportLandedController;

        addSearchBar();
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Flight Number");
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

        // Create a search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.BOLD, 15));
        searchPanel.add(searchButton, "height 40, grow, wrap");

        // Create a JComboBox (dropdown) with options
        String[] options = {
            "Search for Departing Flights", "Search for Already Landed Flights"};
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.PLAIN, 20));
        dropdown.setForeground(Color.DARK_GRAY);
        dropdown.setBackground(Color.PINK);
        searchPanel.add(dropdown, "height 40, grow, span");

        // the search button will execute a different use case depending on the selected option in the JComboBox
        searchButton.addActionListener(event -> {
            System.out.println(searchField.getText());
            String selectedUseCase = (String) dropdown.getSelectedItem();
            switch (selectedUseCase) {
                case "Search for Departing Flights":
                    searchByDepartureAirportController.execute(searchField.getText());
                    break;
                case "Search for Already Landed Flights":
                    searchAirportLandedController.execute(searchField.getText());
                    break;
                default:
                    System.out.println("Error. Selected:" + selectedUseCase);
            }
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void showResult(AbstractState state) {
        String[] columnNames = {"IATA Flight Number", "Arrival Time", "Departure Time", "Departure Airport", "Arrival Airport", "Status"};
        Object[][] data = {{
                state.getFlightNumber(),
                state.getArrivalTime(),
                state.getDepartureTime(),
                state.getDepartureAirport(),
                state.getArrivalAirport(),
                state.getStatus()},

        };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        resultPanel.add(scrollPane, "grow");
        resultPanel.remove(errorLabel);
        // update the panel to show changes
        resultPanel.revalidate();
    }

    private void showError(String errorMessage) {
        // html ensures the text wraps
        errorLabel.setText("<html><body style='width: 300px;'>" + errorMessage + "</body></html>");

        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(SEARCHBYAIRPORT_FONT, Font.BOLD, 20));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);

        resultPanel.remove(scrollPane);
        resultPanel.add(errorLabel, "grow");
        // update the panel to show changes
        resultPanel.revalidate();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param event A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("departureFlightDetails".equals(event.getPropertyName())) {
            showResult(searchByDepartureAirportViewModel.getState());
        }
        else if ("departureFlightError".equals(event.getPropertyName())) {
            showError(searchByDepartureAirportViewModel.getState().getSearchError());
        }
        else if ("landedFlightDetails".equals(event.getPropertyName())) {
            showResult(searchAirportLandedViewModel.getState());
        }
        else if ("landedFlightError".equals(event.getPropertyName())) {
            showError(searchAirportLandedViewModel.getState().getSearchError());
        }
    }
}
