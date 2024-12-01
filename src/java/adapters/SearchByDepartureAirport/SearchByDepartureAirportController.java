package adapters.SearchByDepartureAirport;

import usecase.SearchByDepartureAirport.SearchByDepartureAirportInputBoundary;
import usecase.SearchByDepartureAirport.SearchByDepartureAirportInputData;

/**
 * Controller for the Departure use Case.
 */
public class SearchByDepartureAirportController {
    private final SearchByDepartureAirportInputBoundary searchByDepartureAirportInteractor;

    public SearchByDepartureAirportController(SearchByDepartureAirportInputBoundary searchByFlightNumberInteractor) {
        this.searchByDepartureAirportInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param airportCode the airport code for the airport.
     * @throws IllegalArgumentException when the Airport IATA code is empty
     */
    public void execute(String airportCode) {
        if (airportCode == null || airportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport IATA code cannot be empty.");
        }
        final SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        this.searchByDepartureAirportInteractor.execute(inputData);
    }
}
