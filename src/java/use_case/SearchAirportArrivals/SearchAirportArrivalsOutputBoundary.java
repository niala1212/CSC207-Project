package use_case.SearchAirportArrivals;

/**
 * Output Boundary for the Search by Airport use case.
 */
public interface SearchAirportArrivalsOutputBoundary {
    /**
     * Prepares the success view for the Search by Airport use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchAirportArrivalsOutputData outputData);

    /**
     * Prepares the failure view for the Search by Airport use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchAirportArrivalsOutputData errorMessage);
}
