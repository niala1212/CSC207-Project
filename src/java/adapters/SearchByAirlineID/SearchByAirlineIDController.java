package adapters.SearchByAirlineID;

import use_case.SearchByAirlineID.SearchByAirlineIDInputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDInputData;

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
            throw new IllegalArgumentException("Airline IATA code cannot be empty.");
        }

        // Create input data and call the input boundary
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode.trim());
        searchByAirlineIDInteractor.execute(inputData);
    }
}
