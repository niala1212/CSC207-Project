package use_case.SearchByAirport;

import entities.Flight;
import java.util.List;

/**
 * The Search by Airport Interactor.
 */
public class SearchByAirportInteractor implements SearchByAirportInputBoundary {

    private final SearchByAirportDataAccessInterface flightDataAccessObject;
    private final SearchByAirportOutputBoundary searchByAirportPresenter;

    public SearchByAirportInteractor(SearchByAirportDataAccessInterface flightDataAccessObject,
                                     SearchByAirportOutputBoundary searchByAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirportPresenter = searchByAirportPresenter;
    }

    @Override
    public void execute(SearchByAirportInputData searchByAirportInputData) {
        String airportCode = searchByAirportInputData.getAirportCode();
        List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirport(airportCode);

        if (foundFlights != null && !foundFlights.isEmpty()) {
            SearchByAirportOutputData outputData = new SearchByAirportOutputData(foundFlights);
            searchByAirportPresenter.prepareSuccessView(outputData);
        } else {
            SearchByAirportOutputData outputData = new SearchByAirportOutputData("No flights found for the specified airport.");
            searchByAirportPresenter.prepareFailView(outputData);
        }
    }
}
