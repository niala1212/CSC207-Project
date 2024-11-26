package use_case.SearchAirportLanded;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchAirportLandedInputData {
    private final String airportCode; // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)

    // Constructor to initialize the fields
    public SearchAirportLandedInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public String getAirportCode() {
        return airportCode;
    }
}
