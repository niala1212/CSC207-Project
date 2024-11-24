package use_case.search_by_airline_id;

/**
 * Output Boundary for the Search by Airline ID use case.
 */
public interface SearchByAirlineIDOutputBoundary {
    /**
     * Prepares the success view for the Search by Airline ID use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchByAirlineIDOutputData outputData);

    /**
     * Prepares the failure view for the Search by Airline ID use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SearchByAirlineIDOutputData errorMessage);
}