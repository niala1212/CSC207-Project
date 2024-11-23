package app.gui;

import adapters.ViewManagerModel;

import javax.swing.*;

/**
 * GUI class to run the GUI for the entire Application.
 */
public class Application {
    /**
     * Main method to run the GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame menu = new Menu(viewManagerModel);

            }
        });
    }

}
