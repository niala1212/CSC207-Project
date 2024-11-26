package adapters.SearchByDepartureAirport;

import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputData;

public class SearchByDepartureAirportPresenter implements SearchByDepartureAirportOutputBoundary {
    public SearchByDepartureAirportPresenter(SearchByDepartureAirportViewModel searchByDepartureAirportViewModel) {
    }

    /**
     * Prepares the success view for the Search by Airport use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SearchByDepartureAirportOutputData outputData) {

    }

    /**
     * Prepares the failure view for the Search by Airport use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(SearchByDepartureAirportOutputData errorMessage) {

    }
}
