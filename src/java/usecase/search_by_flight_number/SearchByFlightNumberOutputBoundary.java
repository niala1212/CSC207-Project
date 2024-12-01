package usecase.search_by_flight_number;

/**
 * Output Boundary for the Search by Flight Number use case.
 */
public interface SearchByFlightNumberOutputBoundary {
    /**
     * Prepares the success view for the Search by Flight Number use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByFlightNumberOutputData outputData);

    /**
     * Prepares the failure view for the Search by Flight Number use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchByFlightNumberOutputData errorMessage);
}
