package adapters.SearchAirportLanded;

import usecase.SearchAirportLanded.SearchAirportLandedOutputBoundary;
import usecase.SearchAirportLanded.SearchAirportLandedOutputData;

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
