package app;

import app.gui.Application;

import javax.swing.*;

public class Main {
    /**
     * Main method to run the GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application();
            }
        });
    }

}
