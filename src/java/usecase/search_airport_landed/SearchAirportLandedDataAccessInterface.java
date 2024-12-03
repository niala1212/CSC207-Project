package usecase.search_airport_landed;

import java.io.IOException;
import java.util.List;

import entities.Flight;

/**
 * Data Access Interface for the Search Airport Landed Use Case.
 */
public interface SearchAirportLandedDataAccessInterface {

    /**
     * Retrieves all flights landed at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     * @throws IOException when API not callable.
     */
    List<Flight> getLandedFlightsByAirport(String airportCode) throws IOException;
}
