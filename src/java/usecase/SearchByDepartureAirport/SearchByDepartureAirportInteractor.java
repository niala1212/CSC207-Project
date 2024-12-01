package usecase.SearchByDepartureAirport;

import java.util.List;

import entities.Flight;

/**
 * The interactor for the Search Departure by airport use case.
 */
public class SearchByDepartureAirportInteractor implements SearchByDepartureAirportInputBoundary {

    private final SearchByDepartureAirportDataAccessInterface flightDataAccessObject;
    private final SearchByDepartureAirportOutputBoundary searchByDepartureAirportPresenter;

    public SearchByDepartureAirportInteractor(SearchByDepartureAirportDataAccessInterface flightDataAccessObject,
                                              SearchByDepartureAirportOutputBoundary searchByDepartureAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByDepartureAirportPresenter = searchByDepartureAirportPresenter;
    }

    @SuppressWarnings({"checkstyle:ReturnCount", "checkstyle:SuppressWarnings", "checkstyle:IllegalCatch", "checkstyle:CatchParameterName"})
    @Override
    public void execute(SearchByDepartureAirportInputData searchByDepartureAirportInputData) {
        String airportCode = searchByDepartureAirportInputData.getAirportCode();

        // Validate input data
        if (!isValidAirportCode(airportCode)) {
            // Ensure the view is cleared and the failure message is immediately shown
            handleFailure("Invalid airport code: " + airportCode);
            return;
        }

        try {
            // Fetch flights based on valid airport code
            List<Flight> foundFlights = flightDataAccessObject.getDepartureFlightsByAirport(airportCode);

            // If no flights are found, notify failure
            if (foundFlights == null || foundFlights.isEmpty()) {
                handleFailure("No flights found for the specified airport.");
            }
            else {
                handleSuccess(foundFlights);
            }

        }
        catch (Exception e) {
            handleFailure("An unexpected error occurred. Please try again later.");
        }
    }

    private void handleSuccess(List<Flight> flights) {
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(flights);
        searchByDepartureAirportPresenter.prepareSuccessView(outputData);
    }

    private void handleFailure(String errorMessage) {
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(errorMessage);
        searchByDepartureAirportPresenter.prepareFailView(outputData);
    }

    private boolean isValidAirportCode(String airportCode) {
        // Check for null, empty, and validate format (e.g., IATA codes are 3 uppercase letters)
        return airportCode != null && airportCode.matches("[A-Z]{3}");
    }
}
