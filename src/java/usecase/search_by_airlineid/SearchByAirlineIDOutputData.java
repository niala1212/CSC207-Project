package usecase.search_by_airlineid;

import java.util.List;

import entities.Flight;

/**
 * Output Data for the SearchByAirline Use Case.
 */
public class SearchByAirlineIDOutputData {

    private final List<Flight> filteredFlights;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchByAirlineIDOutputData(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchByAirlineIDOutputData(String errorMessage) {
        this.filteredFlights = null;
        this.errorMessage = errorMessage;
    }

    public final List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public final boolean isUseCaseFailed() {
        return errorMessage != null;
    }
}
