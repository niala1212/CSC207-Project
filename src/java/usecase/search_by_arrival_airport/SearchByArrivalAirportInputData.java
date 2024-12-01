package usecase.search_by_arrival_airport;

/**
 * Input Data for the Search By Airport Use Case.
 */
public class SearchByArrivalAirportInputData {
    // IATA code for the airport (e.g., "JFK" for John F. Kennedy International Airport)
    private final String airportCode;

    // Constructor to initialize the fields
    public SearchByArrivalAirportInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public final String getAirportCode() {
        return airportCode;
    }
}
