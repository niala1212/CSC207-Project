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

import adapters.SearchByFlightNumber.SearchByFlightNumberController;
import adapters.SearchByFlightNumber.SearchByFlightNumberViewModel;
import net.miginfocom.swing.MigLayout;

/**
 * The starting menu showing options: search by flight #, airlines, and airports.
 */
public class Menu extends JFrame {
    static final int MENU_WIDTH = 500;
    static final int MENU_HEIGHT = 300;
    static final int MENU_BORDER = 10;
    static final int MENU_BUTTON_FONT_SIZE = 20;
    static final int MENU_TITLE_FONT_SIZE = 30;
    static final String MENU_FONT = "Arial";

    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;
    private final SearchByFlightNumberController searchByFlightNumberController;

    private final JLabel title = new JLabel("Welcome to the Flight Tracker!");
    private final JButton searchByFlightB = new JButton("Search By Flight Number");
    private final JButton searchByAirportB = new JButton("Search By Airport");
    private final JButton searchByAirlineB = new JButton("Search By Airline");
    private final JButton seeWorldMapB = new JButton("See World Map");
    private final JPanel panel = new JPanel(new MigLayout(
            "insets" + MENU_BUTTON_FONT_SIZE + ", fill"
    ));

    public Menu(SearchByFlightNumberController searchByFlightNumberController,
                SearchByFlightNumberViewModel searchByFlightNumberViewModel) throws HeadlessException {
        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
        this.searchByFlightNumberController = searchByFlightNumberController;

        setTitle("Flight Tracker Application");
        setSize(MENU_WIDTH, MENU_HEIGHT);
        setComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setComponents() {
        panel.setBorder(BorderFactory.createEmptyBorder(MENU_BORDER, 0, 0, 0));

        title.setFont(new Font(MENU_FONT, Font.BOLD, MENU_TITLE_FONT_SIZE));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, "dock north, grow, align center");

        final Font buttonFont = new Font(MENU_FONT, Font.PLAIN, MENU_BUTTON_FONT_SIZE);
        searchByFlightB.setFont(buttonFont);
        panel.add(searchByFlightB, "span, grow");
        searchByFlightB.addActionListener(event -> {
            SearchByFlightFrame searchByFlightFrame =
                    new SearchByFlightFrame(searchByFlightNumberController, searchByFlightNumberViewModel);
        });

        searchByAirportB.setFont(buttonFont);
        panel.add(searchByAirportB, "span, grow");
        searchByAirportB.addActionListener(event -> {
        });

        searchByAirlineB.setFont(buttonFont);
        panel.add(searchByAirlineB, "span, grow");
        searchByAirlineB.addActionListener(event -> {
        });

        seeWorldMapB.setFont(buttonFont);
        panel.add(seeWorldMapB, "span, grow");
        seeWorldMapB.addActionListener(event -> {
        });

        add(panel, BorderLayout.CENTER);
    }
}
