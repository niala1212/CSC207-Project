package use_case;

import entities.Flight;
import java.util.List;

public class SearchFlightByNumberUseCase {

    private List<Flight> flights;

    public SearchFlightByNumberUseCase(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * Searches for a flight by flight number.
     * @param flightNumber the flight number to search for
     * @return the Flight object if found, otherwise null
     */
    public Flight searchByFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null; // No matching flight found
    }
}

