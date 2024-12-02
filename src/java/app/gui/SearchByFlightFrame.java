package app.gui;

// Required imports for Java Swing components and layout
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

// Required imports for the adapters and layout manager
import adapters.search_by_flight_number.SearchByFlightNumberController;
import adapters.search_by_flight_number.SearchByFlightNumberState;
import adapters.search_by_flight_number.SearchByFlightNumberViewModel;
import net.miginfocom.swing.MigLayout;

/**
 * The searchbox to search up flight by IATA code.
 */
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings"})
public class SearchByFlightFrame extends JFrame implements PropertyChangeListener {
    static final int SEARCHBYFLIGHT_WIDTH = 600;
    static final int SEARCHBYFLIGHT_HEIGHT = 400;
    static final String SEARCHBYFLIGHT_FONT = "Arial";
    static final int SEARCH_FIELD_HEIGHT = 40;
    static final int SEARCH_BUTTON_HEIGHT = 40;
    static final int FONT_SIZE_LARGE = 20;
    static final int FONT_SIZE_SMALL = 15;
    static final String PLACEHOLDER_TEXT = "Enter Flight Number (IATA code)";
    static final int ERROR_LABEL_WIDTH = 300;

    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;
    private final SearchByFlightNumberController searchByFlightNumberController;

    // Search and result panels
    private final String placeholderText = "Enter Flight Number (IATA code)";
    private final JPanel searchPanel = new JPanel(new MigLayout("insets 10"));
    private final JLabel errorLabel = new JLabel();
    private final JScrollPane scrollPane = new JScrollPane();
    private final JPanel resultPanel = new JPanel(new MigLayout("insets 10, fill"));

    public SearchByFlightFrame(SearchByFlightNumberController searchByFlightNumberController,
                               SearchByFlightNumberViewModel searchByFlightNumberViewModel) throws HeadlessException {
        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
        this.searchByFlightNumberViewModel.addPropertyChangeListener(this);
        this.searchByFlightNumberController = searchByFlightNumberController;

        // create the search bar JPanel
        addSearchBar();
        // create the result JPanel
        add(resultPanel, BorderLayout.CENTER);

        setTitle("Flight Tracker Search By Flight Number");
        setSize(SEARCHBYFLIGHT_WIDTH, SEARCHBYFLIGHT_HEIGHT);
        setVisible(true);
        // Request focus for the frame itself, not the text field
        requestFocusInWindow();
    }

    private void addSearchBar() {
        JTextField searchField = new JTextField(placeholderText);
        searchField.setColumns(1000000);
        searchField.setForeground(Color.GRAY);
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
        searchField.setFont(new Font(SEARCHBYFLIGHT_FONT, Font.PLAIN, FONT_SIZE_LARGE));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(SEARCHBYFLIGHT_FONT, Font.PLAIN, FONT_SIZE_SMALL));
        searchPanel.add(searchButton, "height 40, grow");
        searchButton.addActionListener(event -> {
            System.out.println(searchField.getText());
            searchByFlightNumberController.execute(searchField.getText());
        });

        add(searchPanel, BorderLayout.NORTH);
    }

    private void showResult(SearchByFlightNumberState searchByFlightNumberState) {
        String[] columnNames = {"IATA Flight Number", "Arrival Time", "Departure Time",
            "Arrival Airport", "Departure Airport", "Status"};
        Object[][] data = {{
            searchByFlightNumberState.getFlightNumber(),
            searchByFlightNumberState.getArrivalTime(),
            searchByFlightNumberState.getDepartureTime(),
            searchByFlightNumberState.getArrivalAirport(),
            searchByFlightNumberState.getDepartureAirport(),
            searchByFlightNumberState.getStatus()},
        };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        resultPanel.add(scrollPane, "grow");
        resultPanel.remove(errorLabel);
        resultPanel.revalidate();
    }

    private void showError(String errorMessage) {
        // html ensures the text wraps
        errorLabel.setText("<html><body style='width: 300px;'>" + errorMessage + "</body></html>");

        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font(SEARCHBYFLIGHT_FONT, Font.BOLD, FONT_SIZE_LARGE));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);

        resultPanel.remove(scrollPane);
        resultPanel.add(errorLabel, "grow");
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
        if ("flightDetails".equals(event.getPropertyName())) {
            showResult(searchByFlightNumberViewModel.getState());
        }
        else if ("error".equals(event.getPropertyName())) {
            showError(searchByFlightNumberViewModel.getState().getError());
        }
    }
}
