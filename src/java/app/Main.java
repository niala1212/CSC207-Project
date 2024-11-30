package app;

import javax.swing.SwingUtilities;

import app.gui.Application;

/**
 * Main class to run the application GUI.
 */
public class Main {
    /**
     * Main method to run the GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Application());
    }
}
