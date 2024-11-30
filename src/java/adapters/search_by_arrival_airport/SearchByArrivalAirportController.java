package adapters.search_by_arrival_airport;

import use_case.SearchByArrivalAirport.SearchByArrivalAirportInputBoundary;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportInputData;

/**
 * The controller for the Search By Arrival Airport Use Case.
 */
public class SearchByArrivalAirportController {

    private final SearchByArrivalAirportInputBoundary searchByArrivalAirportInteractor;

    public SearchByArrivalAirportController(SearchByArrivalAirportInputBoundary searchByArrivalAirportInteractor) {
        this.searchByArrivalAirportInteractor = searchByArrivalAirportInteractor;
    }

    /**
     * Handles the user request to search for flights by arrival airport.
     * @param airportCode the arrival airport IATA code entered by the user
     * @throws IllegalArgumentException when the Airport IATA code is empty
     */
    public void execute(String airportCode) {
        // Checks if the airportCode is empty and if it is, throws a IllegalArgumentException
        if (airportCode == null || airportCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Airport IATA code cannot be empty.");
        }

        // Creates input data and then excecutes the Interactor with the input boundary
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(airportCode.trim());
        searchByArrivalAirportInteractor.execute(inputData);
    }
}
