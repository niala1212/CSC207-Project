package adapters.search_by_flight_number;

import usecase.search_by_flight_number.SearchByFlightNumberInputBoundary;
import usecase.search_by_flight_number.SearchByFlightNumberInputData;

/**
 * The controller for the Search By Flight Number Use Case.
 */
public class SearchByFlightNumberController {
    private final SearchByFlightNumberInputBoundary searchByFlightNumberInteractor;

    public SearchByFlightNumberController(SearchByFlightNumberInputBoundary searchByFlightNumberInteractor) {
        this.searchByFlightNumberInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param flightNumber the flight number entered by the user
     * @throws IllegalArgumentException when the Airport IATA code is empty
     */
    public void execute(String flightNumber) {
        // Checks if the flight number is empty and if it is, throws a IllegalArgumentException
        if (flightNumber == null) {
            throw new IllegalArgumentException("Flight number cannot be null");
        }

        // Creates input data and then excecutes the Interactor with the input boundary
        final SearchByFlightNumberInputData inputData = new SearchByFlightNumberInputData(flightNumber);
        this.searchByFlightNumberInteractor.execute(inputData);
    }
}
