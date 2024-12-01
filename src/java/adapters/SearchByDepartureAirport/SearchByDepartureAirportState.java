package adapters.SearchByDepartureAirport;

import java.util.ArrayList;
import java.util.List;

import adapters.AbstractState;
import entities.Flight;

/**
 * The state for Search Departures by Airport view model.
 */
public class SearchByDepartureAirportState extends AbstractState {
    private String airportName = "";
    private List<String> flightNumbers = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();
    private String message;

    public final String getAirportName() {
        return airportName;
    }

    public final List<String> getFlightNumbers() {
        return flightNumbers;
    }

    public final List<Flight> getFlights() {
        return flights;
    }

    public final String getMessage() {
        return message;
    }

    public final void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public final void setFlightNumbers(List<String> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

    public final void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    public final boolean isSuccessful() {
        return message == null;
    }

    /**
     * The to-string to display all the flights.
     * @param flightNumber flight number of flight
     * @return string of all the flight details
     */
    public String getFlightDetailsString(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.toString();
            }
        }
        return null;
    }
}
