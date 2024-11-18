package use_case.SearchByAirport;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchByAirportInputData {
    private final String airportCode; // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)

    // Constructor to initialize the fields
    public SearchByAirportInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public String getAirportCode() {
        return airportCode;
    }
}
