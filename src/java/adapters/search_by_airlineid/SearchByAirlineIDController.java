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
        if (airlineIataCode == null || airlineIataCode.trim().isEmpty()) {
            // Send error to interactor
            searchByAirlineIDInteractor.execute(new SearchByAirlineIDInputData(null),
                    "Airline IATA code cannot be empty.");
            return;
        }

        String trimmedCode = airlineIataCode.trim();
        if (!trimmedCode.matches("[A-Z0-9]{2}")) {
            // Send validation error to interactor
            searchByAirlineIDInteractor.execute(new SearchByAirlineIDInputData(trimmedCode),
                    "Invalid Airline IATA code.");
            return;
        }

        // Valid input: Proceed with normal execution
        searchByAirlineIDInteractor.execute(new SearchByAirlineIDInputData(trimmedCode), null);
    }
}
