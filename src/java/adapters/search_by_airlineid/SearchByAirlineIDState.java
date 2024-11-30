package adapters.search_by_airlineid;

import java.util.ArrayList;
import java.util.List;

import entities.Flight;

/**
 * The state for the Search By Airline ID View Model.
 */
public class SearchByAirlineIDState {

    private String airlineName = "";
    private List<String> flightNumbers = new ArrayList<>();

    // Full flight data
    private List<Flight> flights = new ArrayList<>();
    private String errorMessage;

    // Getters for UI access
    public final String getAirlineName() {
        return airlineName;
    }

    public final List<String> getFlightNumbers() {
        return flightNumbers;
    }

    public final List<Flight> getFlights() {
        return flights;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    // Setters
    public final void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public final void setFlightNumbers(List<String> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

    public final void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public final void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    // Success Checker
    public final boolean isSuccessful() {
        return errorMessage == null;
    }
}
