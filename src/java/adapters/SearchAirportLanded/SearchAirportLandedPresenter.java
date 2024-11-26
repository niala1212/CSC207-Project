package adapters.SearchAirportLanded;

import use_case.SearchAirportLanded.SearchAirportLandedOutputBoundary;
import use_case.SearchAirportLanded.SearchAirportLandedOutputData;

public class SearchAirportLandedPresenter implements SearchAirportLandedOutputBoundary {
    public SearchAirportLandedPresenter(SearchAirportLandedViewModel searchAirportLandedViewModel) {
    }

    /**
     * Prepares the success view for the Search by Airport use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SearchAirportLandedOutputData outputData) {

    }

    /**
     * Prepares the failure view for the Search by Airport use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(SearchAirportLandedOutputData errorMessage) {

    }
}
