package adapters;

import use_case.SearchByFlightNumber.SearchByFlightNumberInputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberInputData;


public class SearchByFlightNumberController {
    private final SearchByFlightNumberInputBoundary searchByFlightNumberInteractor;

    public SearchByFlightNumberController(SearchByFlightNumberInputBoundary searchByFlightNumberInteractor) {
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
