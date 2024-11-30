package use_case.SeeWorldMap;

import entities.Flight;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

/**
 * Output Data for the SeeWorldMap Use Case.
 */
public class SeeWorldMapOutputData {
    static final int SWM_WIDTH = 1000;
    static final int SWM_HEIGHT = 700;

    private final List<Flight> filteredFlights;
    private Map<Point2D.Double, Flight> flightMarkers;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SeeWorldMapOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.flightMarkers = generateFlightMarkers(filteredFlights);
        this.errorMessage = null;
    }

    private Map<Point2D.Double, Flight> generateFlightMarkers(List<Flight> filteredFlights) {
        flightMarkers = new HashMap<>();
        for (Flight flight : filteredFlights) {
            if (flight.getCoordinates() != null) {
                double[] coords = flight.getCoordinates();
                double lat = coords[0];
                double lon = coords[1];
                double x = (lon + 180) * (SWM_WIDTH / 360) + 123.4;
                double latRad = lat * PI / 180;
                double mercN = log(tan((PI / 4) + (latRad / 2)));
                double y = ((SWM_HEIGHT + 20.0) / 2) - ((SWM_WIDTH-290.0) * mercN / (2 * PI));
                Point2D.Double point = new Point2D.Double(x, y);
                System.out.println(point);
                System.out.println(flight);
                flightMarkers.put(point, flight);
            }
        }
        return flightMarkers;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SeeWorldMapOutputData(String errorMessage) {
        this.filteredFlights = null;
        this.flightMarkers = null;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public Map<Point2D.Double, Flight> getFlightMarkers() { return flightMarkers; }
}
