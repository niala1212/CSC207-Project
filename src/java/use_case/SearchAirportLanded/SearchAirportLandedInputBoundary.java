package use_case.SearchByDepartureAirport;

/**
 * Input Boundary for actions which are related to searching by Airport.
 */
public interface SearchAirportLandedInputBoundary {

    /**
     * Executes the search by Airport use case.
     * @param searchAirportLandedInputData the input data
     */
    void execute(use_case.SearchByDepartureAirport.SearchAirportLandedInputData searchAirportLandedInputData);
}
