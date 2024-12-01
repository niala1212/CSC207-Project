package usecase.search_by_arrival_airport;

/**
 * Output Boundary for the Search by Airport use case.
 */
public interface SearchByArrivalAirportOutputBoundary {
    /**
     * Prepares the success view for the Search by Airport use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByArrivalAirportOutputData outputData);

    /**
     * Prepares the failure view for the Search by Airport use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchByArrivalAirportOutputData errorMessage);
}
