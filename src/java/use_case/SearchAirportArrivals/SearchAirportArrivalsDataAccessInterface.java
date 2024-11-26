package use_case.SearchByDepartureAirport;

import entities.Flight;
import java.util.List;

/**
 * Data Access Interface for the Search By Airport Use Case.
 */
public interface SearchAirportArrivalsDataAccessInterface {

    /**
     * Retrieves all flights departing or arriving at the given airport code.
     *
     * @param airportCode the IATA code of the airport
     * @return a list of flights for the given airport
     */
    List<Flight> getFlightsByAirport(String airportCode);
}
