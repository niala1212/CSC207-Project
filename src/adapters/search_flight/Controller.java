package adapters.search_flight;

import use_case.SearchByFlightNumber.SearchByFlightNumberInputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberInputData;

/**
 * The controller for the search use case.
 */
public class SearchController {

    private final SearchByFlightNumberInputBoundary searchByFlightNumberInteractor;

    public SearchController(SearchByFlightNumberInputBoundary searchByFlightNumberInteractor) {
        this.searchByFlightNumberInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param flightNumber
     */
    public void execute(String flightNumber) {
        final SearchByFlightNumberInputData inputData = new SearchByFlightNumberInputData(flightNumber);
        this.searchByFlightNumberInteractor.execute(inputData);

    }
}
