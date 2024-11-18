package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

/**
 * The searchbox to search up flight #s, airlines, and airports.
 */
public class SearchBox extends JFrame {
    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JTable searchResult = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(searchResult);
    private final JPanel searchPanel = new JPanel(new MigLayout(
            "insets 10"
    ));
    private final JPanel resultPanel = new JPanel(new MigLayout(
            "insets 10, fill"
    ));

    public SearchBox(int width, int height) throws HeadlessException {
        super("Flight Tracker Search Box");
        setTable();

        pack();
        searchField.setColumns(getWidth());
        addComponents();
        setSize(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addComponents() {
        searchPanel.add(searchField, "height 40");
        searchPanel.add(searchButton, "height 40");
        resultPanel.add(scrollPane, "grow");

        add(searchPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    private void setTable() {
        searchButton.addActionListener(event -> {
            System.out.println("Searched: " + searchField.getText());
//            searchResult.setModel(DbUtils.resultSetToTableModel(
//                    new DataBase().search(searchField.getText(), panel)));
        });
    }
}
