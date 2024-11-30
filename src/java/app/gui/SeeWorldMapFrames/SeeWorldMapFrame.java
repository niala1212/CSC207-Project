package app.gui.SeeWorldMapFrames;

import adapters.SeeWorldMap.SeeWorldMapController;
import adapters.SeeWorldMap.SeeWorldMapState;
import adapters.SeeWorldMap.SeeWorldMapViewModel;
import entities.Flight;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class SeeWorldMapFrame extends JFrame implements PropertyChangeListener {
    static final int SWM_WIDTH = 1000;
    static final int SWM_HEIGHT = 700;
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
        setSize(SWM_WIDTH+200, SWM_HEIGHT+200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        addGenButton();
        addStaticMap();
//        addCalibrationPoints();
    }

    private void addCalibrationPoints() {
        BufferedImage mapImage = new BufferedImage(SWM_WIDTH, SWM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = mapImage.createGraphics();
        ImageIcon baseMap = (ImageIcon) mapLabel.getIcon();
        List<Point2D.Double> markers = new ArrayList<>();
        markers.add(new Point2D.Double(123.4, 9.0));
        Flight flight = new Flight("eh", "oh");
        Flight flight2 = new Flight("eh", "oh");
        double[] currentLocation = {0.0, 0.0};
        double[] currentLocation2 = {42.6304, 141.882};
        flight.setCurrentLocation(currentLocation);
        flight2.setCurrentLocation(currentLocation2);

        List<Flight> filteredFlights = new ArrayList<>();
        filteredFlights.add(flight);
        filteredFlights.add(flight2);

        for (Flight fl : filteredFlights) {
            double[] coords = fl.getCoordinates();
            double lat = coords[0];
            double lon = coords[1];
            double x = (lon + 180) * (SWM_WIDTH / 360) + 123.4;
            double latRad = lat * PI / 180;
            double mercN = log(tan((PI / 4) + (latRad / 2)));
            double y = ((SWM_HEIGHT + 20.0) / 2) - ((SWM_WIDTH-290.0) * mercN / (2 * PI));
            Point2D.Double point = new Point2D.Double(x, y);
            System.out.println(point);
            markers.add(point);
        }

        if (baseMap != null) {
            g.drawImage(baseMap.getImage(), 0, 0, SWM_WIDTH, SWM_HEIGHT, null);

            // Draw markers on the map
            g.setColor(Color.RED);
            for (Point2D.Double marker : markers) {
                // Adjust marker coordinates and size as needed
                g.fillOval((int) marker.x, (int) marker.y, 10, 10);
            }
        }
        mapLabel.setIcon(new ImageIcon(mapImage));
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
        updateMapImage();
    }

    private void updateMapImage() {
        try {
            // Download the static map image
            String destinationFile = "image.jpg";
            downloadImage(destinationFile);

            // Set the downloaded image as the map label's icon
            ImageIcon mapIcon = new ImageIcon(destinationFile);
            Image scaledImage = mapIcon.getImage().getScaledInstance(SWM_WIDTH, SWM_HEIGHT, Image.SCALE_SMOOTH);
            mapLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
            mapLabel.setText("Error loading map image");
        }
    }

    private void downloadImage(String destinationFile) throws IOException {
//        URL url = new URL("https://www.worldatlas.com/r/w1000/upload/ee/ef/d8/mercator-projection-world-map-political.png");

//        URL url = new URL("https://gisgeography.com/wp-content/uploads/2015/04/Plate-Carree-Projection.png");
        URL url = new URL("https://images.vliz.be/thumbs/64927_mercator-projection.png?w=1000");
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
                    g.fillOval((int) marker.x, (int) marker.y, 10, 10);
                }
            } else {
                g.setColor(Color.BLACK);
                g.drawString("Error: Base map not loaded", 10, 20);
            }
        } finally {
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
//            SeeWorldMapState state = (SeeWorldMapState) evt.getNewValue();
            showResult(seeWorldMapViewModel.getState());
        }
    }
}
