package adapters.search_by_airlineid;

import usecase.search_by_airlineid.SearchByAirlineIDInputBoundary;
import usecase.search_by_airlineid.SearchByAirlineIDInputData;

/**
 * The controller for the Search By Airline ID Use Case.
 */
public class SearchByAirlineIDController {

    private final SearchByAirlineIDInputBoundary searchByAirlineIDInteractor;

    public SearchByAirlineIDController(SearchByAirlineIDInputBoundary searchByAirlineIDInteractor) {
        this.searchByAirlineIDInteractor = searchByAirlineIDInteractor;
    }

    /**
     * Handles the user request to search for flights by an airline's IATA code.
     * @param airlineIataCode the airline IATA code entered by the user.
     */
    public void execute(String airlineIataCode) {
        String trimmedCode = airlineIataCode.trim();

        // Valid input: Proceed with normal execution
        searchByAirlineIDInteractor.execute(new SearchByAirlineIDInputData(trimmedCode));
    }
}
