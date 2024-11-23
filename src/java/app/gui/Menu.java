package app.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import adapters.ViewManagerModel;
import net.miginfocom.swing.MigLayout;

/**
 * The starting menu showing options: search by flight #, airlines, and airports.
 */
public class Menu extends JFrame implements PropertyChangeListener {
    static final int MENU_WIDTH = 500;
    static final int MENU_HEIGHT = 300;
    static final int MENU_BORDER = 10;
    static final int MENU_BUTTON_FONT_SIZE = 20;
    static final int MENU_TITLE_FONT_SIZE = 30;
    static final String MENU_FONT = "Arial";

    private final ViewManagerModel viewManagerModel;

    private final JLabel title = new JLabel("Welcome to the Flight Tracker!");
    private final JButton searchByFlightB = new JButton("Search By Flight Number");
    private final JButton searchByAirportB = new JButton("Search By Airport");
    private final JButton searchByAirlineB = new JButton("Search By Airline");
    private final JButton seeWorldMapB = new JButton("See World Map");
    private final JPanel panel = new JPanel(new MigLayout(
            "insets" + MENU_BUTTON_FONT_SIZE + ", fill"
    ));

    public Menu(ViewManagerModel viewManagerModel) throws HeadlessException {
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addPropertyChangeListener(this);

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
            viewManagerModel.setState(ViewManagerModel.State.SEARCHBYFLIGHT);
        });

        searchByAirportB.setFont(buttonFont);
        panel.add(searchByAirportB, "span, grow");
        searchByAirportB.addActionListener(event -> {
            viewManagerModel.setState(ViewManagerModel.State.SEARCHBYAIRPORTID);
        });

        searchByAirlineB.setFont(buttonFont);
        panel.add(searchByAirlineB, "span, grow");
        searchByAirlineB.addActionListener(event -> {
            viewManagerModel.setState(ViewManagerModel.State.SEARCHBYAIRLINEID);
        });

        seeWorldMapB.setFont(buttonFont);
        panel.add(seeWorldMapB, "span, grow");
        seeWorldMapB.addActionListener(event -> {
            viewManagerModel.setState(ViewManagerModel.State.SEEWORLDMAP);
        });

        add(panel, BorderLayout.CENTER);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ViewManagerModel.State newValue = (ViewManagerModel.State) evt.getNewValue();
            switch (newValue) {
                case SEARCHBYFLIGHT:
                    SearchByFlightFrame searchByFlightFrame = new SearchByFlightFrame(viewManagerModel);
                case SEARCHBYAIRLINEID:
                    ;
                case SEARCHBYAIRPORTID:
                    ;
                case SEEWORLDMAP:
                    ;
//                default -> throw new IllegalStateException("Unexpected value: " + newValue);
            }
        }

    }
}
