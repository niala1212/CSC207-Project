package usecase.search_airport_landed;

import java.util.List;

import entities.Flight;

/**
 * The Search by Landed Airport Interactor.
 */
public class SearchAirportLandedInteractor implements SearchAirportLandedInputBoundary {

    private final SearchAirportLandedDataAccessInterface flightDataAccessObject;
    private final SearchAirportLandedOutputBoundary searchAirportLandedPresenter;

    public SearchAirportLandedInteractor(SearchAirportLandedDataAccessInterface flightDataAccessObject,
                                         SearchAirportLandedOutputBoundary searchAirportLandedPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchAirportLandedPresenter = searchAirportLandedPresenter;
    }

    @Override
    public void execute(SearchAirportLandedInputData searchAirportLandedInputData) {
        String airportCode = searchAirportLandedInputData.getAirportCode();

        try {
            List<Flight> foundFlights = flightDataAccessObject.getLandedFlightsByAirport(airportCode);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(
                        "Error retrieving the requested landed flights at \"" + airportCode
                                + "\". Please enter a valid IATA airport code.");
                searchAirportLandedPresenter.prepareFailView(outputData);
            }
            else if (foundFlights.isEmpty()) {
                // No flights found by the API
                SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(
                        "Sorry, no landed flights have been found at the airport \"" + airportCode + "\"");
                searchAirportLandedPresenter.prepareFailView(outputData);
            }
            else {
                // Flight found: Success
                SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(foundFlights);
                searchAirportLandedPresenter.prepareSuccessView(outputData);
            }
        }
        catch (Exception error) {
            // Unexpected failure: Handle exceptions
            SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(
                    "An unexpected error occurred: " + error.getMessage());
            searchAirportLandedPresenter.prepareFailView(outputData);
        }
    }
}
