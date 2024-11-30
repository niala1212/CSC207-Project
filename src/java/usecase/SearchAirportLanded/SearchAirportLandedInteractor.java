package usecase.SearchAirportLanded;

import entities.Flight;
import java.util.List;

/**
 * The Search by Airport Interactor.
 */
public class SearchAirportLandedInteractor implements SearchAirportLandedInputBoundary {

    private final SearchAirportLandedDataAccessInterface flightDataAccessObject;
    private final SearchAirportLandedOutputBoundary searchByAirportPresenter;

    public SearchAirportLandedInteractor(SearchAirportLandedDataAccessInterface flightDataAccessObject,
                                         SearchAirportLandedOutputBoundary searchByAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirportPresenter = searchByAirportPresenter;
    }

    @Override
    public void execute(SearchAirportLandedInputData searchAirportLandedInputData) {
        String airportCode = searchAirportLandedInputData.getAirportCode();
        List<Flight> foundFlights = flightDataAccessObject.getLandedFlightsByAirport(airportCode);

        if (foundFlights != null && !foundFlights.isEmpty()) {
            SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(foundFlights);
            searchByAirportPresenter.prepareSuccessView(outputData);
        } else {
            SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData("No flights found for the specified airport.");
            searchByAirportPresenter.prepareFailView(outputData);
        }
    }
}
