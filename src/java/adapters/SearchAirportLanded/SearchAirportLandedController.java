package adapters.SearchAirportLanded;

import use_case.SearchAirportLanded.SearchAirportLandedInputBoundary;
import use_case.SearchAirportLanded.SearchAirportLandedInputData;


public class SearchAirportLandedController {
    private final SearchAirportLandedInputBoundary searchAirportLandedInteractor;

    public SearchAirportLandedController(SearchAirportLandedInputBoundary searchByFlightNumberInteractor) {
        this.searchAirportLandedInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param airportCode
     */
    public void execute(String airportCode) {
        final SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(airportCode);
        this.searchAirportLandedInteractor.execute(inputData);
    }
}