package usecase.see_world_map;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Flight;

/**
 * Output Data for the SeeWorldMap Use Case.
 */
public class SeeWorldMapOutputData {
    static final int SWM_WIDTH = 1000;
    static final int SWM_HEIGHT = 700;

    private static final int LONGITUDE_OFFSET = 180;
    private static final int LONGITUDE_DEVISOR = 360;
    private static final double LATITUDE_CONVERSION_FACTOR = Math.PI / 180;
    private static final double MERCATOR_SCALING_FACTOR = Math.PI / 4;
    private static final double EXTRA_Y_OFFSET = 20.0;
    private static final double MAP_OFFSET_X = 123.4;
    private static final double MAP_OFFSET_Y = 290.0;

    private final List<Flight> filteredFlight;
    private Map<Point2D.Double, Flight> flightMarkers;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SeeWorldMapOutputData(List<Flight> filteredFlights) {
        this.filteredFlight = filteredFlights;
        this.flightMarkers = generateFlightMarkers(filteredFlights);
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SeeWorldMapOutputData(String errorMessage) {
        this.filteredFlight = null;
        this.flightMarkers = null;
        this.errorMessage = errorMessage;
    }

    private Map<Point2D.Double, Flight> generateFlightMarkers(List<Flight> filteredFlights) {
        flightMarkers = new HashMap<>();
        for (Flight flight : filteredFlights) {
            if (flight.getCoordinates() != null) {
                double[] coords = flight.getCoordinates();
                double lat = coords[0];
                double lon = coords[1];
                double x = (lon + LONGITUDE_OFFSET) * (SWM_WIDTH / LONGITUDE_DEVISOR) + MAP_OFFSET_X;
                double latRad = lat * LATITUDE_CONVERSION_FACTOR;
                double mercN = Math.log(Math.tan(MERCATOR_SCALING_FACTOR + (latRad / 2)));
                double y = ((SWM_HEIGHT + EXTRA_Y_OFFSET) / 2) - ((SWM_WIDTH - MAP_OFFSET_Y) * mercN / (2 * Math.PI));
                Point2D.Double point = new Point2D.Double(x, y);
                System.out.println(point);
                System.out.println(flight);
                flightMarkers.put(point, flight);
            }
        }
        return flightMarkers;
    }

    /**
     * Access the coordinate markers for the map.
     * @return the markers for the flights
     */
    public Map<Point2D.Double, Flight> getFlightMarkers() {
        return flightMarkers;
    }

    /**
     * Return error.
     * @return error
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
