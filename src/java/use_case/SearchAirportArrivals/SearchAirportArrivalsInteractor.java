package use_case.SearchAirportArrivals;

import entities.Flight;
import java.util.List;

/**
 * The Search by Airport Interactor.
 */
public class SearchAirportArrivalsInteractor implements SearchAirportArrivalsInputBoundary {

    private final SearchAirportArrivalsDataAccessInterface flightDataAccessObject;
    private final SearchAirportArrivalsOutputBoundary searchByAirportPresenter;

    public SearchAirportArrivalsInteractor(SearchAirportArrivalsDataAccessInterface flightDataAccessObject,
                                           SearchAirportArrivalsOutputBoundary searchByAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirportPresenter = searchByAirportPresenter;
    }

    @Override
    public void execute(SearchAirportArrivalsInputData SearchAirportArrivalsInputData) {
        String airportCode = SearchAirportArrivalsInputData.getAirportCode();
        List<Flight> foundFlights = flightDataAccessObject.getArrivalFlightsByAirport(airportCode);

        if (foundFlights != null && !foundFlights.isEmpty()) {
            SearchAirportArrivalsOutputData outputData = new SearchAirportArrivalsOutputData(foundFlights);
            searchByAirportPresenter.prepareSuccessView(outputData);
        } else {
            SearchAirportArrivalsOutputData outputData = new SearchAirportArrivalsOutputData("No flights found for the specified airport.");
            searchByAirportPresenter.prepareFailView(outputData);
        }
    }
}
