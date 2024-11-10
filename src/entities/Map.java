package entities;
import java.util.List;

/**
 * The representation of a map in our program.
 */

public class Map {
    private int mapID;
    private List<Flight> flightList;
    private int zoomLevel;
    private String centreCoordinates;

    // Constructor
    public Map(int mapID, List<Flight> flightList, int zoomLevel, String centreCoordinates) {
        this.mapID = mapID;
        this.flightList = flightList;
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

    public String getCentreCoordinates() {
        return centreCoordinates;
    }

    // Setters
    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public void setCentreCoordinates(String centreCoordinates) {
        this.centreCoordinates = centreCoordinates;
    }

    public void addFlight(Flight flight) {
        if (flightList != null && flight != null) {
            flightList.add(flight);
        }
    }

    public void updateCentreCoordinates(String newCoordinates) {
        this.centreCoordinates = newCoordinates;
    }
}
