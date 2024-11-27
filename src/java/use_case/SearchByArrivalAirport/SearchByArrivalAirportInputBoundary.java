package use_case.SearchByArrivalAirport;

/**
 * Input Boundary for actions which are related to searching by Airport.
 */
public interface SearchByArrivalAirportInputBoundary {

    /**
     * Executes the search by Airport use case.
     * @param searchByAirportInputData the input data
     */
    void execute(SearchByArrivalAirportInputData searchByAirportInputData);
}
