package use_case.SearchByAirport;

import entities.Flight;
import java.util.List;

/**
 * Output Data for the Search By Airport Use Case.
 */
public class SearchByAirportOutputData {

    private final List<Flight> filteredFlights;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchByAirportOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchByAirportOutputData(String errorMessage) {
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
