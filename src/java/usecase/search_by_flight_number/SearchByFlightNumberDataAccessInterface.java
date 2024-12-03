package usecase.search_by_flight_number;

import java.io.IOException;

import entities.Flight;

/**
 * The interface of the DAO for the search_by_flight_number Use Case.
 */
public interface SearchByFlightNumberDataAccessInterface {

    /**
     * Retrieves the flight from the system given the IATA flight number.
     * @param flightNumber the IATA flight number
     * @param flightDate the flight date
     * @return a flight
     * @throws IOException when API not callable
     */
    Flight getFlightByFlightNumber(String flightNumber, String flightDate) throws IOException;
}
