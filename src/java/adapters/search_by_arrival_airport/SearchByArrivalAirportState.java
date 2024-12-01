package adapters.search_by_arrival_airport;

import java.util.ArrayList;
import java.util.List;

import entities.Flight;

/**
 * State for the Search By Arrival Airport Use Case.
 */
public class SearchByArrivalAirportState {

    private String airportName = "";
    private List<String> flightNumbers = new ArrayList<>();

    // Full flight data
    private List<Flight> flights = new ArrayList<>();
    private String message;

    // Getters for UI access
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

    /**
     * To String method to get flight details.
     * @param flightNumber the Flight number needed to convert
     * @return flight in string form
     */
    public String getFlightDetailsString(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                // Formats this string as needed
                return flight.toString();
            }
        }
        // Returns null if no flight is found
        return null;
    }

    // Success checker
    public final boolean isSuccessful() {
        return message == null;
    }

}
