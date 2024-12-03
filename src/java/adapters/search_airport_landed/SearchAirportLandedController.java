package adapters.search_airport_landed;

import usecase.search_airport_landed.SearchAirportLandedInputBoundary;
import usecase.search_airport_landed.SearchAirportLandedInputData;

/**
 * Controller for the Search Airport Landed use case.
 */
public class SearchAirportLandedController {
    private final SearchAirportLandedInputBoundary searchAirportLandedInteractor;

    public SearchAirportLandedController(SearchAirportLandedInputBoundary searchByFlightNumberInteractor) {
        this.searchAirportLandedInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param airportCode input
     */
    public void execute(String airportCode) {
        final SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(airportCode);
        this.searchAirportLandedInteractor.execute(inputData);
    }
}
