package use_case.search_by_airline_id;

/**
 * Input Boundary for actions which are related to searching by Airline ID.
 */
public interface SearchByAirlineIDInputBoundary {

    /**
     * Executes the search by Airline ID use case.
     * @param searchByAirlineIDInputData the input data
     */
    void execute(SearchByAirlineIDInputData searchByAirlineIDInputData);
}
