package usecase.search_by_airlineid;

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
