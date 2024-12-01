package usecase.search_by_flight_number;

/**
 * Input Boundary for actions which are related to searching by Flight Number.
 */
public interface SearchByFlightNumberInputBoundary {

    /**
     * Execute the search_by_flight_number Use Case.
     * @param SearchByFlightNumberInputData the input data for this use case
     */
    void execute(SearchByFlightNumberInputData SearchByFlightNumberInputData);
}
