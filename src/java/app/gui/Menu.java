package app.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import adapters.ViewModel;
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

    private final ViewModel viewModel;

    private final JLabel title = new JLabel("Welcome to the Flight Tracker!");
    private final JButton searchByFlight = new JButton("Search By Flight Number");
    private final JButton searchByAirport = new JButton("Search By Airport");
    private final JButton searchByAirline = new JButton("Search By Airline");
    private final JButton seeWorldMap = new JButton("See World Map");
    private final JPanel panel = new JPanel(new MigLayout(
            "insets" + MENU_BUTTON_FONT_SIZE + ", fill"
    ));

    public Menu(ViewModel viewModel) throws HeadlessException {
        this.viewModel = viewModel;

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
        searchByFlight.setFont(buttonFont);
        panel.add(searchByFlight, "span, grow");
        searchByFlight.addActionListener(event -> {
            SearchByFlightFrame searchByFlightFrame = new SearchByFlightFrame(viewModel);
        });

        searchByAirport.setFont(buttonFont);
        panel.add(searchByAirport, "span, grow");

        searchByAirline.setFont(buttonFont);
        panel.add(searchByAirline, "span, grow");

        seeWorldMap.setFont(buttonFont);
        panel.add(seeWorldMap, "span, grow");

        add(panel, BorderLayout.CENTER);
    }

}
