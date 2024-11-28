package app.gui.SeeWorldMapFrames;

import adapters.SeeWorldMap.SeeWorldMapController;
import adapters.SeeWorldMap.SeeWorldMapState;
import adapters.SeeWorldMap.SeeWorldMapViewModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class SeeWorldMapFrame extends JFrame implements PropertyChangeListener {
    static final int SWM_WIDTH = 1000;
    static final int SWM_HEIGHT = 600;
    static final String SWM_FONT = "Arial";

    private final SeeWorldMapViewModel seeWorldMapViewModel;
    private final SeeWorldMapController seeWorldMapController;

    private final JButton genButton = new JButton("Generate");
    private final JPanel panel = new JPanel(new MigLayout("insets 10, fill"));
    private final JLabel mapLabel = new JLabel(); // To display the map image

    public SeeWorldMapFrame(SeeWorldMapController seeWorldMapController,
                            SeeWorldMapViewModel seeWorldMapViewModel) throws HeadlessException {
        this.seeWorldMapController = seeWorldMapController;
        this.seeWorldMapViewModel = seeWorldMapViewModel;
        this.seeWorldMapViewModel.addPropertyChangeListener(this);

        setTitle("See World Map");
        setSize(SWM_WIDTH, SWM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        addGenButton();
        addStaticMap();
    }

    private void addGenButton() {
        genButton.setForeground(Color.GRAY);
        genButton.addActionListener(event -> seeWorldMapController.execute());

        // Add the button to the top panel
        panel.add(genButton, "span, grow");
        add(panel, BorderLayout.NORTH);
    }

    private void addStaticMap() {
        // Configure the map label to display the map image
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapLabel.setPreferredSize(new Dimension(SWM_WIDTH, SWM_HEIGHT));
        add(mapLabel, BorderLayout.CENTER);

        // Load the initial static map image
        updateMapImage("https://www.worldatlas.com/r/w1200-q200/upload/cd/e1/c3/physical-map-of-world-in-mercator-projection.png");
    }

    private void updateMapImage(String imageUrl) {
        try {
            // Download the static map image
            String destinationFile = "image.jpg";
            downloadImage(imageUrl, destinationFile);

            // Set the downloaded image as the map label's icon
            ImageIcon mapIcon = new ImageIcon(destinationFile);
            Image scaledImage = mapIcon.getImage().getScaledInstance(SWM_WIDTH, SWM_HEIGHT, Image.SCALE_SMOOTH);
            mapLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
            mapLabel.setText("Error loading map image");
        }
    }

    private void downloadImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream is = url.openStream();
             OutputStream os = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[2048];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
        }
    }

    private void showResult(SeeWorldMapState seeWorldMapState) {
        // Render markers on the map based on the state
        List<Point> markers = seeWorldMapState.getMarkers(); // Assume the state provides a list of markers

        BufferedImage mapImage = new BufferedImage(SWM_WIDTH, SWM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mapImage.createGraphics();

        // Draw the static map
        ImageIcon baseMap = (ImageIcon) mapLabel.getIcon();
        if (baseMap != null) {
            g.drawImage(baseMap.getImage(), 0, 0, SWM_WIDTH, SWM_HEIGHT, null);

            // Draw flight markers
            g.setColor(Color.RED);
            for (Point marker : markers) {
                g.fillOval(marker.x - 5, marker.y - 5, 10, 10); // Simple marker circle
            }
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Error: Base map not loaded", 10, 20);
        }

        g.dispose();
        mapLabel.setIcon(new ImageIcon(mapImage));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("seeWorldMapState".equals(evt.getPropertyName())) {
            // Update the map when the state changes
            SeeWorldMapState state = (SeeWorldMapState) evt.getNewValue();
            showResult(state);
        }
    }
}
