package usecase.search_by_airlineid;

import java.util.List;

import entities.Flight;

/**
 * The Search by Airline ID Interactor.
 */
public class SearchByAirlineIDInteractor implements SearchByAirlineIDInputBoundary {

    private final SearchByAirlineIDDataAccessInterface flightDataAccessObject;
    private final SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter;

    public SearchByAirlineIDInteractor(SearchByAirlineIDDataAccessInterface flightDataAccessObject,
                                       SearchByAirlineIDOutputBoundary searchByAirlineIDPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirlineIDPresenter = searchByAirlineIDPresenter;
    }

    @SuppressWarnings({"checkstyle:ReturnCount", "checkstyle:SuppressWarnings"})
    @Override
    public void execute(SearchByAirlineIDInputData searchByAirlineIDInputData) {
        String airlineId = searchByAirlineIDInputData.getAirlineIataCode();

        // Validation: check if the airline ID is valid
        if (airlineId == null || airlineId.trim().isEmpty()) {
            // Handle validation failure
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "Airline IATA code cannot be empty.");
            searchByAirlineIDPresenter.prepareFailView(outputData);
            return;
        }

        String trimmedCode = airlineId.trim();
        if (!trimmedCode.matches("[A-Za-z0-9]{2}")) {
            // Handle validation failure
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "Invalid Airline IATA code.");
            searchByAirlineIDPresenter.prepareFailView(outputData);
            return;
        }

        // If validation passes, proceed with normal execution
        try {
            List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirlineId(trimmedCode);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                        "Error retrieving flight data for the specified airline. Please try a different IATA.");
                searchByAirlineIDPresenter.prepareFailView(outputData);

            }
            else if (foundFlights.isEmpty()) {
                // No flights found: Valid but empty result
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                        "No flights found for the specified airline.");
                searchByAirlineIDPresenter.prepareSuccessView(outputData);

            }
            else {
                // Flights found: Success
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(foundFlights);
                searchByAirlineIDPresenter.prepareSuccessView(outputData);
            }
        }
        catch (Exception error) {
            // Unexpected failure: Handle exceptions
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "An unexpected error occurred:\n" + "API ERROR");
            searchByAirlineIDPresenter.prepareFailView(outputData);
        }
    }
}
