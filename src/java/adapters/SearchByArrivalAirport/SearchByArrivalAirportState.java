package adapters.SearchByArrivalAirport;

import entities.Flight;
import java.util.List;

public class SearchByArrivalAirportState {

    private String airportName;
    private List<String> flightNumbers;
    private List<Flight> flights;  // Full flight data
    private String message;

    // Constructor success with flight data
    public SearchByArrivalAirportState(String airportName, List<String> flightNumbers, List<Flight> flights) {
        this.airportName = airportName;
        this.flightNumbers = flightNumbers;
        this.flights = flights;
        this.message = null;
    }

    // Constructor for failure with error message
    public SearchByArrivalAirportState(String message) {
        this.message = message;
        this.airportName = null;
        this.flightNumbers = null;
        this.flights = null;
    }

    // Getters for UI access
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

    public boolean isSuccessful() {
        return message == null;
    }
}
