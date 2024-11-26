package use_case.SearchByDepartureAirport;

/**
 * Input Boundary for actions which are related to searching by Airport.
 */
public interface SearchByDepartureAirportInputBoundary {

        /**
         * Executes the search by Airport use case.
         * @param searchByDepartureAirportInputData the input data
         */
        void execute(SearchByDepartureAirportInputData searchByDepartureAirportInputData);
}
