package app.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByFlightNumber.SearchByFlightNumberController;
import adapters.SearchByFlightNumber.SearchByFlightNumberViewModel;
import net.miginfocom.swing.MigLayout;

/**
 * The starting menu showing options: search by flight number, airlines, and airports.
 */
public class Menu extends JFrame {
    // Constants for the menu UI layout and styling
    static final int MENU_WIDTH = 500;
    static final int MENU_HEIGHT = 300;
    static final int MENU_BORDER = 10;
    static final int MENU_BUTTON_FONT_SIZE = 20;
    static final int MENU_TITLE_FONT_SIZE = 30;
    static final String MENU_FONT = "Arial";

    // Controllers and ViewModels for the search functionality
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;
    private final SearchByFlightNumberController searchByFlightNumberController;
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel;
    private final SearchByAirlineIDController searchByAirlineIDController;

    // UI Components
    private final JLabel title = new JLabel("Welcome to the Flight Tracker!");
    private final JButton searchByFlightB = new JButton("Search By Flight Number");
    private final JButton searchByAirportArrivals = new JButton("Search Arrivals By Airport");
    private final JButton searchByAirportDepartures = new JButton("Search Departures By Airport");
    private final JButton searchByAirlineB = new JButton("Search By Airline");
    private final JButton seeWorldMapB = new JButton("See World Map");
    private final JPanel panel = new JPanel(new MigLayout(
            "insets" + MENU_BUTTON_FONT_SIZE + ", fill"
    ));

    // Constructor to initialize the frame with controllers and view models
    public Menu(SearchByFlightNumberController searchByFlightNumberController,
                SearchByFlightNumberViewModel searchByFlightNumberViewModel,
                SearchByAirlineIDController searchByAirlineIDController,
                SearchByAirlineIDViewModel searchByAirlineIDViewModel) throws HeadlessException {
        // Initialize controllers and view models
        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
        this.searchByFlightNumberController = searchByFlightNumberController;
        this.searchByAirlineIDViewModel = searchByAirlineIDViewModel;
        this.searchByAirlineIDController = searchByAirlineIDController;

        // Set the window title and size
        setTitle("Flight Tracker Application");
        setSize(MENU_WIDTH, MENU_HEIGHT);

        // Initialize and set up the components in the frame
        setComponents();

        // Set default close operation and make the window visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to set up all the components of the menu
    private void setComponents() {
        // Set border for the main panel
        panel.setBorder(BorderFactory.createEmptyBorder(MENU_BORDER, 0, 0, 0));

        // Set title label properties
        title.setFont(new Font(MENU_FONT, Font.BOLD, MENU_TITLE_FONT_SIZE));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, "dock north, grow, align center");

        // Set the font for buttons
        final Font buttonFont = new Font(MENU_FONT, Font.PLAIN, MENU_BUTTON_FONT_SIZE);

        // Setup Search By Flight Number button and action listener
        searchByFlightB.setFont(buttonFont);
        panel.add(searchByFlightB, "span, grow");
        searchByFlightB.addActionListener(event -> {
            // Opens the SearchByFlightFrame for flight number search
            SearchByFlightFrame searchByFlightFrame =
                    new SearchByFlightFrame(searchByFlightNumberController, searchByFlightNumberViewModel);
        });

        // Setup Search By Airport button (to be implemented later)
        searchByAirportArrivals.setFont(buttonFont);
        panel.add(searchByAirportArrivals, "span, grow");
        searchByAirportArrivals.addActionListener(event -> {
            // Placeholder for airport search functionality
        });

        // Setup Search By Airport button (to be implemented later)
        searchByAirportDepartures.setFont(buttonFont);
        panel.add(searchByAirportDepartures, "span, grow");
        searchByAirportDepartures.addActionListener(event -> {
            // Placeholder for airport search functionality
        });

        // Setup Search By Airline button and action listener
        searchByAirlineB.setFont(buttonFont);
        panel.add(searchByAirlineB, "span, grow");
        searchByAirlineB.addActionListener(event -> {
            // Opens the SearchByAirlineIDFrame for airline search
            SearchByAirlineIDFrame searchByAirlineFrame =
                    new SearchByAirlineIDFrame(searchByAirlineIDController, searchByAirlineIDViewModel);
        });

        // Setup See World Map button (to be implemented later)
        seeWorldMapB.setFont(buttonFont);
        panel.add(seeWorldMapB, "span, grow");
        seeWorldMapB.addActionListener(event -> {
            // Placeholder for world map functionality
        });

        // Add the panel to the center of the frame
        add(panel, BorderLayout.CENTER);
    }
}
