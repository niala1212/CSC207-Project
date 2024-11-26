package use_case.SearchByDepartureAirport;

/**
 * Output Boundary for the Search Departures by Airport use case.
 */
public interface SearchByDepartureAirportOutputBoundary {
    /**
     * Prepares the success view for the Search by Airport use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByDepartureAirportOutputData outputData);

    /**
     * Prepares the failure view for the Search by Airport use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchByDepartureAirportOutputData errorMessage);
}
