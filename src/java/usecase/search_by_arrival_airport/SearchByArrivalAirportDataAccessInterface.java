package usecase.search_by_arrival_airport;

import java.util.List;

import entities.Flight;

/**
 * Data Access Interface for the Search By Arrival Airport Use Case.
 */
public interface SearchByArrivalAirportDataAccessInterface {

    /**
     * Retrieves all flights arriving at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of arrivalflights for the given airport
     */
    List<Flight> getArrivalFlights(String airportCode);

}
