package usecase.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

public class SearchByDepartureAirportInteractor implements SearchByDepartureAirportInputBoundary {

    private final SearchByDepartureAirportDataAccessInterface flightDataAccessObject;
    private final SearchByDepartureAirportOutputBoundary searchByDepartureAirportPresenter;

    public SearchByDepartureAirportInteractor(SearchByDepartureAirportDataAccessInterface flightDataAccessObject,
                                              SearchByDepartureAirportOutputBoundary searchByDepartureAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByDepartureAirportPresenter = searchByDepartureAirportPresenter;
    }

    public void execute(SearchByDepartureAirportInputData searchByDepartureAirportInputData) {
        String airportCode = searchByDepartureAirportInputData.getAirportCode();
        List<Flight> foundFlights = flightDataAccessObject.getDepartureFlightsByAirport(airportCode);

        if (foundFlights != null && !foundFlights.isEmpty()) {
            SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(foundFlights);
            searchByDepartureAirportPresenter.prepareSuccessView(outputData);
        } else {
            SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData("No flights found for the specified airport.");
            searchByDepartureAirportPresenter.prepareFailView(outputData);
        }
    }
}
