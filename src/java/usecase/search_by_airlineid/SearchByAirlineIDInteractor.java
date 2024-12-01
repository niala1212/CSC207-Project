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

    @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:CatchParameterName", "checkstyle:SuppressWarnings"})
    @Override
    public void execute(SearchByAirlineIDInputData searchByAirlineIDInputData) {
        String airlineId = searchByAirlineIDInputData.getAirlineIataCode();

        try {
            List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirlineId(airlineId);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                        "Error retrieving flight data for the specified airline. Please try a different iata.");
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
        catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(
                    "An unexpected error occurred:\n" + "API ERROR");
            searchByAirlineIDPresenter.prepareFailView(outputData);
        }
    }
}
