package use_case.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

/**
 * The Search by Airport Interactor.
 */
public class SearchAirportArrivalsInteractor implements use_case.SearchByDepartureAirport.SearchAirportLandedInputBoundary {

    private final SearchAirportArrivalsDataAccessInterface flightDataAccessObject;
    private final use_case.SearchByDepartureAirport.SearchAirportLandedOutputBoundary searchByAirportPresenter;

    public SearchAirportArrivalsInteractor(SearchAirportArrivalsDataAccessInterface flightDataAccessObject,
                                           use_case.SearchByDepartureAirport.SearchAirportLandedOutputBoundary searchByAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByAirportPresenter = searchByAirportPresenter;
    }

    @Override
    public void execute(use_case.SearchByDepartureAirport.SearchAirportLandedInputData searchAirportLandedInputData) {
        String airportCode = searchAirportLandedInputData.getAirportCode();
        List<Flight> foundFlights = flightDataAccessObject.getFlightsByAirport(airportCode);

        if (foundFlights != null && !foundFlights.isEmpty()) {
            use_case.SearchByDepartureAirport.SearchAirportLandedOutputData outputData = new use_case.SearchByDepartureAirport.SearchAirportLandedOutputData(foundFlights);
            searchByAirportPresenter.prepareSuccessView(outputData);
        } else {
            use_case.SearchByDepartureAirport.SearchAirportLandedOutputData outputData = new use_case.SearchByDepartureAirport.SearchAirportLandedOutputData("No flights found for the specified airport.");
            searchByAirportPresenter.prepareFailView(outputData);
        }
    }
}
