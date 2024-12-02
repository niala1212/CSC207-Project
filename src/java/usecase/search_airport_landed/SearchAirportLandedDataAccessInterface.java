package usecase.search_airport_landed;

import entities.Flight;
import java.util.List;

/**
 * Data Access Interface for the Search Airport Landed Use Case.
 */
public interface SearchAirportLandedDataAccessInterface {

    /**
     * Retrieves all flights landed at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     */
    List<Flight> getLandedFlightsByAirport(String airportCode);
}
