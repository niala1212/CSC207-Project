package app.gui.see_world_map_frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import adapters.see_world_map.SeeWorldMapController;
import adapters.see_world_map.SeeWorldMapState;
import adapters.see_world_map.SeeWorldMapViewModel;
import net.miginfocom.swing.MigLayout;

public class SeeWorldMapFrame extends JFrame implements PropertyChangeListener {
    static final int SWM_WIDTH = 1000;
    static final int SWM_HEIGHT = 700;
    static final int FRAME_OFFSET = 200;

    private final SeeWorldMapViewModel seeWorldMapViewModel;
    private final SeeWorldMapController seeWorldMapController;

    private final JButton genButton = new JButton("Generate");
    private final JPanel panel = new JPanel(new MigLayout("insets 10, fill"));
    private final JLabel mapLabel = new JLabel();

    public SeeWorldMapFrame(SeeWorldMapController seeWorldMapController,
                            SeeWorldMapViewModel seeWorldMapViewModel) throws HeadlessException, IOException {
        this.seeWorldMapController = seeWorldMapController;
        this.seeWorldMapViewModel = seeWorldMapViewModel;
        this.seeWorldMapViewModel.addPropertyChangeListener(this);

        setTitle("See World Map");
        setSize(SWM_WIDTH + FRAME_OFFSET, SWM_HEIGHT + FRAME_OFFSET);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        setVisible(true);
    }

    private void initComponents() throws IOException {
        addGenButton();
        addStaticMap();
    }

    private void addGenButton() {
        genButton.setForeground(Color.GRAY);
        genButton.addActionListener(event -> {
            try {
                seeWorldMapController.execute();
            }
            catch (IOException error) {
                throw new RuntimeException(error);
            }
        });

        // Add the button to the top panel
        panel.add(genButton, "span, grow");
        add(panel, BorderLayout.NORTH);
    }

    private void addStaticMap() throws IOException {
        // Configure the map label to display the map image
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapLabel.setPreferredSize(new Dimension(SWM_WIDTH, SWM_HEIGHT));
        add(mapLabel, BorderLayout.CENTER);

        // Load the initial static map image
        updateMapImage();
    }

    private void updateMapImage() throws IOException {
        // Download the static map image
        String destinationFile = "image.jpg";
        downloadImage(destinationFile);

        // Set the downloaded image as the map label's icon
        ImageIcon mapIcon = new ImageIcon(destinationFile);
        Image scaledImage = mapIcon.getImage().getScaledInstance(SWM_WIDTH, SWM_HEIGHT, Image.SCALE_SMOOTH);
        mapLabel.setIcon(new ImageIcon(scaledImage));
    }

    private void downloadImage(String destinationFile) throws IOException {
        final int bits = 2048;
        URL url = new URL("https://images.vliz.be/thumbs/64927_mercator-projection.png?w=1000");
        try (InputStream is = url.openStream();
             OutputStream os = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[bits];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
        }
    }

    private void showResult(SeeWorldMapState seeWorldMapState) {
        final int pointSize = 10;
        // Retrieve the list of markers
        List<Point2D.Double> markers = seeWorldMapState.getMarkers();

        // Create a new BufferedImage for the map
        BufferedImage mapImage = new BufferedImage(SWM_WIDTH, SWM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mapImage.createGraphics();

        try {
            // Draw the base map image
            ImageIcon baseMap = (ImageIcon) mapLabel.getIcon();
            if (baseMap != null) {
                g.drawImage(baseMap.getImage(), 0, 0, SWM_WIDTH, SWM_HEIGHT, null);

                // Draw markers on the map
                g.setColor(Color.RED);
                for (Point2D.Double marker : markers) {
                    // Adjust marker coordinates and size as needed
                    g.fillOval((int) marker.x, (int) marker.y, pointSize, pointSize);
                }
            }
            else {
                g.setColor(Color.BLACK);
                g.drawString("Error: Base map not loaded", pointSize, pointSize);
            }
        }
        finally {
            // Ensure Graphics2D resources are released
            g.dispose();
        }
        // Update the JLabel with the newly created map image
        mapLabel.setIcon(new ImageIcon(mapImage));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("flightDetails".equals(evt.getPropertyName())) {
            // Update the map when the state changes
            showResult(seeWorldMapViewModel.getState());
        }
    }
}
