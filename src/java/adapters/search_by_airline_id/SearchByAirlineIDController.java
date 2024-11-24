package adapters.search_by_airline_id;

import use_case.search_by_airline_id.SearchByAirlineIDInputBoundary;
import use_case.search_by_airline_id.SearchByAirlineIDInputData;

/**
 * The controller for the Search By Airline ID Use Case.
 */
public class SearchByAirlineIDController {

    private final SearchByAirlineIDInputBoundary searchByAirlineIDInteractor;

    public SearchByAirlineIDController(SearchByAirlineIDInputBoundary searchByAirlineIDInteractor) {
        this.searchByAirlineIDInteractor = searchByAirlineIDInteractor;
    }

    /**
     * Executes the Search By Airline ID Use Case.
     * @param airlineIataCode the airline IATA code entered by the user.
     */
    public void execute(String airlineIataCode) {
        if (airlineIataCode == null || airlineIataCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airline IATA code cannot be empty.");
        }

        // Create input data and call the interactor
        final SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode.trim());
        searchByAirlineIDInteractor.execute(inputData);
    }
}
