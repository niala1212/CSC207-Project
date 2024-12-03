package usecase.search_airport_landed;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchAirportLandedInputData {
    // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)
    private final String airportCode;

    // Constructor to initialize the fields
    public SearchAirportLandedInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public final String getAirportCode() {
        return airportCode;
    }
}
