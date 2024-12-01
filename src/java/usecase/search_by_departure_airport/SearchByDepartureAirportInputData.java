package usecase.search_by_departure_airport;

/**
 * Input Data for the Search Departures By Airport Use Case.
 */
public class SearchByDepartureAirportInputData {
    private final String airportCode;

    // Constructor to initialize the fields
    public SearchByDepartureAirportInputData(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter
    public final String getAirportCode() {
        return airportCode;
    }
}
