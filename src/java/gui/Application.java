package gui;

import javax.swing.*;

/**
 * GUI class to run the GUI for the entire Application.
 */
public class Application {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;

    /**
     * Main method to run the GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new SearchBox(WIDTH, HEIGHT);

            }
        });
    }

}
