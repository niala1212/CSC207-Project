package usecase.search_by_arrival_airport;

import java.util.List;

import entities.Flight;

/**
 * The Search by Arrival Airport Interactor.
 */
public class SearchByArrivalAirportInteractor implements SearchByArrivalAirportInputBoundary {

    private final SearchByArrivalAirportDataAccessInterface flightDataAccessObject;
    private final SearchByArrivalAirportOutputBoundary searchByArrivalAirportPresenter;

    public SearchByArrivalAirportInteractor(SearchByArrivalAirportDataAccessInterface flightDataAccessObject,
                                            SearchByArrivalAirportOutputBoundary searchByArrivalAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByArrivalAirportPresenter = searchByArrivalAirportPresenter;
    }

    @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:CatchParameterName",
        "checkstyle:SuppressWarnings", "checkstyle:ReturnCount"})
    @Override
    public void execute(SearchByArrivalAirportInputData searchByAirportInputData) {
        String airportCode = searchByAirportInputData.getAirportCode();

        // Validation: check if the airport code is valid
        if (airportCode == null || airportCode.trim().isEmpty()) {
            // Handle validation failure
            SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData(
                    "Airport code cannot be empty.");
            searchByArrivalAirportPresenter.prepareFailView(outputData);
            return;
        }

        String trimmedCode = airportCode.trim();
        if (!trimmedCode.matches("[A-Za-z0-9]{3}")) {
            // Handle validation failure
            SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData(
                    "Invalid Airline IATA code.");
            searchByArrivalAirportPresenter.prepareFailView(outputData);
            return;
        }

        try {
            List<Flight> arrivalFlights = flightDataAccessObject.getArrivalFlights(airportCode);

            if (arrivalFlights == null) {
                // Critical failure: API or connection issue
                SearchByArrivalAirportOutputData outputData =
                        new SearchByArrivalAirportOutputData("Error retrieving flight data. Please try again later.");
                searchByArrivalAirportPresenter.prepareFailView(outputData);
            }
            else if (arrivalFlights.isEmpty()) {
                // No flights found: Valid but empty result
                SearchByArrivalAirportOutputData outputData =
                        new SearchByArrivalAirportOutputData("No arrival flights found for the specified airport.");
                searchByArrivalAirportPresenter.prepareSuccessView(outputData);
            }
            else {
                // Flights found: Success
                SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData(arrivalFlights);
                searchByArrivalAirportPresenter.prepareSuccessView(outputData);
            }
        }
        catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByArrivalAirportOutputData outputData =
                    new SearchByArrivalAirportOutputData("An unexpected error occurred");
            searchByArrivalAirportPresenter.prepareFailView(outputData);
        }
    }
}
