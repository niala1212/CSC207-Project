package use_case.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

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
