package usecase.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

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

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public String getDepartureErrorMessage() {
        return departureErrorMessage;
    }

    public boolean isUseCaseFailed() {
        return departureErrorMessage != null;
    }
}
