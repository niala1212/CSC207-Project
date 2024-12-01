package usecase.search_by_departure_airport;

import java.util.List;

import entities.Flight;

/**
 * Data Access Interface for the Search Departures By Airport Use Case.
 */
public interface SearchByDepartureAirportDataAccessInterface {

    /**
     * Retrieves all flights departing at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     */
    List<Flight> getDepartureFlightsByAirport(String airportCode);
}
