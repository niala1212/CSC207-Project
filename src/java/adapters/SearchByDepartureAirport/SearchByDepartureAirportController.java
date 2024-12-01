package adapters.SearchByDepartureAirport;

import usecase.SearchByDepartureAirport.SearchByDepartureAirportInputBoundary;
import usecase.SearchByDepartureAirport.SearchByDepartureAirportInputData;


public class SearchByDepartureAirportController {
    private final SearchByDepartureAirportInputBoundary SearchByDepartureAirportInteractor;

    public SearchByDepartureAirportController(SearchByDepartureAirportInputBoundary searchByFlightNumberInteractor) {
        this.SearchByDepartureAirportInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param airportCode
     */
    public void execute(String airportCode) {
        if (airportCode == null || airportCode.trim().isEmpty()) {
            return;
        }
        final SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        this.SearchByDepartureAirportInteractor.execute(inputData);
    }
}