package use_case.SearchByArrivalAirport;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchByArrivalAirportInputData {
    private final String airportCode; // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)

    // Constructor to initialize the fields
    public SearchByArrivalAirportInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public String getAirportCode() {
        return airportCode;
    }
}
