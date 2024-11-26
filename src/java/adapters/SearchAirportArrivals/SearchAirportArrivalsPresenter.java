package adapters.SearchAirportArrivals;

import use_case.SearchAirportArrivals.SearchAirportArrivalsOutputBoundary;
import use_case.SearchAirportArrivals.SearchAirportArrivalsOutputData;

public class SearchAirportArrivalsPresenter implements SearchAirportArrivalsOutputBoundary {
    public SearchAirportArrivalsPresenter(SearchAirportArrivalsViewModel searchAirportArrivalsViewModel) {
    }

    /**
     * Prepares the success view for the Search by Airport use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SearchAirportArrivalsOutputData outputData) {

    }

    /**
     * Prepares the failure view for the Search by Airport use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(SearchAirportArrivalsOutputData errorMessage) {

    }
}
