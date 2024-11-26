package use_case.SearchAirportArrivals;

/**
 * Input Boundary for actions which are related to searching by Airport.
 */
public interface SearchAirportArrivalsInputBoundary {

    /**
     * Executes the search by Airport use case.
     * @param SearchAirportArrivalsInputData the input data
     */
    void execute(SearchAirportArrivalsInputData SearchAirportArrivalsInputData);
}
