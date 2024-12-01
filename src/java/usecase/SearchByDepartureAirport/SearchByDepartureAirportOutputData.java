package usecase.SearchByDepartureAirport;

import java.util.List;

import entities.Flight;

/**
 * Output Data for the Search By Airport Use Case.
 */
public class SearchByDepartureAirportOutputData {

    private final List<Flight> departureFlights;
    private final String departureErrorMessage;

    // Constructor for success (with filtered flights)
    public SearchByDepartureAirportOutputData(List<Flight> filteredFlights) {
        this.departureFlights = filteredFlights;
        this.departureErrorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchByDepartureAirportOutputData(String departureErrorMessage) {
        this.departureFlights = null;
        this.departureErrorMessage = departureErrorMessage;
    }

    public final List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public final String getDepartureErrorMessage() {
        return departureErrorMessage;
    }

    public final boolean isUseCaseFailed() {
        return departureErrorMessage != null;
    }
}
