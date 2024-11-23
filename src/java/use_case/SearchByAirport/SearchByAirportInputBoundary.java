package use_case.SearchByAirport;

/**
 * Input Boundary for actions which are related to searching by Airport.
 */
public interface SearchByAirportInputBoundary {

    /**
     * Executes the search by Airport use case.
     * @param searchByAirportInputData the input data
     */
    void execute(SearchByAirportInputData searchByAirportInputData);
}
