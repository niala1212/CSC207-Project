package use_case.SearchByAirlineID;

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
    public void execute(SearchByAirlineIDInputData searchByAirlineIDInputData, String error) {
        // If there's an error message, skip processing and show the error
        if (error != null) {
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(error);
            searchByAirlineIDPresenter.prepareFailView(outputData);
            return;
        }

        String airlineId = searchByAirlineIDInputData.getAirlineIataCode();

        // Additional check for empty or null airline ID
        if (airlineId == null || airlineId.trim().isEmpty()) {
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "Airline IATA code cannot be empty.");
            searchByAirlineIDPresenter.prepareFailView(outputData);
            return;
        }

        try {
            List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirlineId(airlineId);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                        "Error retrieving flight data for the specified airline. Please try a different iata.");
                searchByAirlineIDPresenter.prepareFailView(outputData);

            } else if (foundFlights.isEmpty()) {
                // No flights found: Valid but empty result
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                        "No flights found for the specified airline.");
                searchByAirlineIDPresenter.prepareSuccessView(outputData);

            } else {
                // Flights found: Success
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(foundFlights);
                searchByAirlineIDPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "An unexpected error occurred:\n" + "API ERROR");
            searchByAirlineIDPresenter.prepareFailView(outputData);
        }
    }
}
