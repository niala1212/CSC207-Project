package usecase.search_by_airlineid;

import java.io.IOException;
import java.util.List;

import entities.Flight;

/**
 * Data Access Interface for the Search By Airline ID Use Case.
 */
public interface SearchByAirlineIDDataAccessInterface {

    /**
     * Retrieves all flights for the given airline ID.
     *
     * @param airlineId the (IATA) code of the airline
     * @return a list of flights for the given airline ID
     */
    List<Flight> getFlightsByAirlineId(String airlineId) throws IOException;
}
