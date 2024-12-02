package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The representation of a map in our program.
 */
public class Map {
    private int mapID;
    private final List<Flight> flightList;
    private int zoomLevel;
    private double[] centreCoordinates;

    // Default Constructor
    public Map() {
        // Initialize to an empty list
        this.flightList = new ArrayList<>();
    }

    // Parameterized Constructor
    public Map(int mapID, List<Flight> flightList, int zoomLevel, double[] centreCoordinates) {
        this.mapID = mapID;
        this.flightList = Objects.requireNonNullElseGet(flightList, ArrayList::new);
        this.zoomLevel = zoomLevel;
        this.centreCoordinates = centreCoordinates;
    }

    // Getters

    public int getMapID() {
        return mapID;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public double[] getCentreCoordinates() {
        return centreCoordinates;
    }

    // Setters

    public void addFlight(Flight flight) {
        if (flightList != null && flight != null) {
            for (Flight existingFlight : flightList) {
                if (existingFlight.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                    // Flight is already in the list, so we skip adding
                    return;
                }
            }
            // No duplicate found, so add the flight
            flightList.add(flight);
        }
    }

    public void updateCentreCoordinates(double[] newCoordinates) {
        this.centreCoordinates = newCoordinates;
    }

    public void updateZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}
