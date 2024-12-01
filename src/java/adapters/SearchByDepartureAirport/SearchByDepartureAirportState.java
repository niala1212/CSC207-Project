package adapters.SearchByDepartureAirport;

import adapters.AbstractState;
import entities.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for Search Departures by Airport view model
 */
public class SearchByDepartureAirportState extends AbstractState {
    private String airportName = "";
    private List<String> flightNumbers = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();  // Full flight data
    private String message = null;

    public String getAirportName() {
        return airportName;
    }

    public List<String> getFlightNumbers() {
        return flightNumbers;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public String getMessage() {
        return message;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setFlightNumbers(List<String> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return message == null;
    }

    // Notify view to clear results and set empty message
    public void clearPreviousResults() {
        this.message = "";  // Clear any message
        this.flightNumbers = new ArrayList<>();  // Clear the flight list
        this.flights = new ArrayList<>();  // Clear flight data
    }

    /**
     * The to-string to display all the flights
     * @param flightNumber flight number of flight
     * @return string of all the flight details
     */
    public String getFlightDetailsString(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.toString(); // Format this string as needed
            }
        }
        return null; // Return null if no flight is found
    }
}
