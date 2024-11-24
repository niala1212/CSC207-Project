package use_case.SearchByFlightNumber;

import entities.Flight;

/**
 * The interface of the DAO for the SearchByFlightNumber Use Case.
 */
public interface SearchByFlightNumberDataAccessInterface {

    /**
     * Retrieves the flight from the system given the IATA flight number.
     * @param flightNumber the IATA flight number
     * @return a list of flights
     */
    Flight getFlightByFlightNumber(String flightNumber);
}
