package app.gui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import adapters.ViewManagerModel;
import net.miginfocom.swing.MigLayout;

/**
 * The searchbox to search up flight by IATA code.
 */
public class SearchByFlightFrame extends JFrame {
    static final int SEARCHBYFLIGHT_WIDTH = 600;
    static final int SEARCHBYFLIGHT_HEIGHT = 600;
    static final String SEARCHBYFLIGHT_FONT = "Arial";

    private final ViewManagerModel viewManagerModel;

    private final String placeholderText = "Enter Flight Number (IATA code)";
    private final JTextField searchField = new JTextField(placeholderText);
    private final JButton searchButton = new JButton("Search");
    private final JTable searchResult = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(searchResult);
    private final JPanel searchPanel = new JPanel(new MigLayout(
            "insets 10"
    ));
    private final JPanel resultPanel = new JPanel(new MigLayout(
            "insets 10, fill"
    ));

    public SearchByFlightFrame(ViewManagerModel viewManagerModel) throws HeadlessException {
        this.viewManagerModel = viewManagerModel;

        setTitle("Flight Tracker Search By Flight Number");
        setSize(SEARCHBYFLIGHT_WIDTH, SEARCHBYFLIGHT_HEIGHT);
        setComponents();

        // set state to MENU when window closes
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                viewManagerModel.setState(ViewManagerModel.State.MENU);
            }
        });

        setVisible(true);
        // Request focus for the frame itself, not the text field
        requestFocusInWindow();
    }

    private void setComponents() {
        setTable();

        searchField.setColumns(getWidth());
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
        searchField.setFont(new Font(SEARCHBYFLIGHT_FONT, Font.PLAIN, 20));

        searchPanel.add(searchButton, "height 40, grow");

        resultPanel.add(scrollPane, "grow");

        add(searchPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void setTable() {
        searchButton.addActionListener(event -> {

        });
    }
}
