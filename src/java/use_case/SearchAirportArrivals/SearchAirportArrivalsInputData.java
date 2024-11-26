package use_case.SearchAirportArrivals;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchAirportArrivalsInputData {
    private final String airportCode; // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)

    // Constructor to initialize the fields
    public SearchAirportArrivalsInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public String getAirportCode() {
        return airportCode;
    }
}
