package use_case.search_by_airline_id;

/**
 * Input Data for the Search By Airline ID Use Case.
 */
public class SearchByAirlineIDInputData {
    private final String airlineCode; // IATA code for the airline (e.g., "5J" for Cebu Pacific)

    // Constructor to initialize the fields
    public SearchByAirlineIDInputData(String airlineIataCode) {
        this.airlineCode = airlineIataCode;
    }

    // Getters
    public String getAirlineIataCode() {
        return airlineCode;
    }
}
