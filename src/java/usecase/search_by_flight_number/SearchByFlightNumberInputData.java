package usecase.search_by_flight_number;

/**
 * The input data for the search_by_flight_number Use Case.
 */
public class SearchByFlightNumberInputData {
    // (e.g., "187")
    private final String flightNumber;

    // Constructor to initialize the fields
    public SearchByFlightNumberInputData(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    // Getters
    public final String getFlightNumber() {
        return flightNumber;
    }
}
