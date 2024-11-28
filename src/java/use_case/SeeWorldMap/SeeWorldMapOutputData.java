package use_case.SeeWorldMap;

import entities.Flight;
import java.util.List;

/**
 * Output Data for the SeeWorldMap Use Case.
 */
public class SeeWorldMapOutputData {

    private final List<Flight> filteredFlights;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SeeWorldMapOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SeeWorldMapOutputData(String errorMessage) {
        this.filteredFlights = null;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public List<Flight> getWorldMapFlights() {
        return filteredFlights;
    }
}
