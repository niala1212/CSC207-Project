package adapters.SearchByArrivalAirport;

import use_case.SearchByArrivalAirport.SearchByArrivalAirportInputBoundary;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportInputData;

/**
 * The controller for the Search By Airport Use Case.
 */
public class SearchByArrivalAirportController {

    private final SearchByArrivalAirportInputBoundary searchByArrivalAirportInteractor;

    public SearchByArrivalAirportController(SearchByArrivalAirportInputBoundary searchByArrivalAirportInteractor) {
        this.searchByArrivalAirportInteractor = searchByArrivalAirportInteractor;
    }

    /**
     * Handles the user request to search for flights by airport
     * @param airportCode the airport IATA code entered by the user
     */
    public void execute(String airportCode) {
        if (airportCode == null || airportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport IATA code cannot be empty.");
        }

        // Create input data and call the input boundary
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(airportCode.trim());
        searchByArrivalAirportInteractor.execute(inputData);
    }
}
