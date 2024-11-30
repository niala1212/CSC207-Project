package adapters.search_by_airlineid;

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
     * @throws IllegalArgumentException when the Airport IATA code is empty
     */
    public void execute(String airlineIataCode) {
        // Checks if the airport IATA Code is null and if it is, throws a IllegalArgumentException
        if (airlineIataCode == null || airlineIataCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airline IATA code cannot be empty.");
        }

        // Creates input data and then excecutes the Interactor with the input boundary
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode.trim());
        searchByAirlineIDInteractor.execute(inputData);
    }
}
