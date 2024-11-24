package use_case.search_by_airline_id;

import entities.Flight;
import java.util.List;

/**
 * The Search by Airline ID Interactor.
 */
public class SearchByAirlineIDInteractor implements SearchByAirlineIDInputBoundary{

    private final SearchByAirlineIDDataAccessInterface flightDataAccessObject;
    private final SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter;

    public SearchByAirlineIDInteractor(SearchByAirlineIDDataAccessInterface flightDataAccessObject,
                                       SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirlineIDPresenter = searchByAirlineIDPresenter;
    }

    @Override
    public void execute(SearchByAirlineIDInputData searchByAirlineIDInputData) {
        String airlineId = searchByAirlineIDInputData.getAirlineIataCode();

        try {
            List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirlineId(airlineId);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData("Error retrieving flight data. Please try again later.");
                searchByAirlineIDPresenter.prepareFailView(outputData);
            } else if (foundFlights.isEmpty()) {
                // No flights found: Valid but empty result
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData("No flights found for the specified airline.");
                searchByAirlineIDPresenter.prepareSuccessView(outputData);
            } else {
                // Flights found: Success
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(foundFlights);
                searchByAirlineIDPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData("An unexpected error occurred: " + e.getMessage());
            searchByAirlineIDPresenter.prepareFailView(outputData);
        }
    }
}
