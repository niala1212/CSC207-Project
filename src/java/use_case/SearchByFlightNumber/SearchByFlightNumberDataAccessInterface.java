package use_case.SearchByFlightNumber;

import entities.Flight;

/**
 * The interface of the DAO for the search_by_flight_number Use Case.
 */
public interface SearchByFlightNumberDataAccessInterface {

    /**
     * Retrieves the flight from the system given the IATA flight number.
     * @param flightNumber the IATA flight number
     * @return a flight
     */
    Flight getFlightByFlightNumber(String flightNumber, String flightDate);
}
