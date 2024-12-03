package adapters.see_world_map;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.AbstractState;
import entities.Flight;

/**
 * The state for the See World Map ViewModel.
 */
public class SeeWorldMapState extends AbstractState {

    // Map holding marker points and associated flight information
    private final Map<Point2D.Double, Flight> markerInfo = new HashMap<>();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Returns a list of all marker positions (keys in the map).
     *
     * @return List of marker positions
     */
    public List<Point2D.Double> getMarkers() {
        return List.copyOf(markerInfo.keySet());
    }

    /**
     * Retrieves flight information associated with a specific marker point.
     *
     * @param point The point to look up
     * @return Flight information or null if not found
     */
    public String getFlightInfo(Point2D.Double point) {
        return markerInfo.get(point).toString();
    }

    /**
     * Adds a marker with its associated flight information.
     *
     * @param point      The marker's position
     * @param flightInfo The associated flight information
     */
    public void addMarker(Point2D.Double point, Flight flightInfo) {
        markerInfo.put(point, flightInfo);

        // Notify listeners of a state update
        notifyListeners();
    }

    /**
     * Clears all markers from the state.
     */
    public void clearMarkers() {
        markerInfo.clear();

        // Notify listeners of a state update
        notifyListeners();
    }

    private void notifyListeners() {
        support.firePropertyChange("seeWorldMapState", null, this);
    }
}
