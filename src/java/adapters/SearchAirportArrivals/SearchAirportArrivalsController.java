package adapters.SearchAirportArrivals;

import use_case.SearchAirportArrivals.SearchAirportArrivalsInputBoundary;
import use_case.SearchAirportArrivals.SearchAirportArrivalsInputData;

public class SearchAirportArrivalsController {
    private final SearchAirportArrivalsInputBoundary searchAirportArrivalsInteractor;

    public SearchAirportArrivalsController(SearchAirportArrivalsInputBoundary searchByFlightNumberInteractor) {
        this.searchAirportArrivalsInteractor = searchByFlightNumberInteractor;
    }

    /**
     * Executes the search by flight number use case.
     * @param airportCode the airport code given/inputted
     */
    public void execute(String airportCode) {
        final SearchAirportArrivalsInputData inputData = new SearchAirportArrivalsInputData(airportCode);
        this.searchAirportArrivalsInteractor.execute(inputData);
    }
}
