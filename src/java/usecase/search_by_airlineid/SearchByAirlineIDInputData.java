package usecase.search_by_airlineid;

/**
 * Input Data for the Search By Airline ID Use Case.
 */
public class SearchByAirlineIDInputData {
    // IATA code for the airline (e.g., "5J" for Cebu Pacific)
    private final String airlineCode;

    // Constructor to initialize the fields
    public SearchByAirlineIDInputData(String airlineIataCode) {
        this.airlineCode = airlineIataCode;
    }

    // Getters
    public final String getAirlineIataCode() {
        return airlineCode;
    }
}
