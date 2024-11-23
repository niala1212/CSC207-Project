package use_case.SearchByAirport;

/**
 * Output Boundary for the Search by Airport use case.
 */
public interface SearchByAirportOutputBoundary {
    /**
     * Prepares the success view for the Search by Airport use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByAirportOutputData outputData);

    /**
     * Prepares the failure view for the Search by Airport use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchByAirportOutputData errorMessage);
}
