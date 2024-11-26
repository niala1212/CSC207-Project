package use_case.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

/**
 * Output Data for the Search By Airport Use Case.
 */
public class SearchAirportLandedOutputData {

    private final List<Flight> filteredFlights;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchAirportLandedOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchAirportLandedOutputData(String errorMessage) {
        this.filteredFlights = null;
        this.errorMessage = errorMessage;
    }

    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isUseCaseFailed() {
        return errorMessage != null;
    }
}
