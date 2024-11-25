package adapters.SearchByAirlineID;

import entities.Flight;
import java.util.List;

public class SearchByAirlineIDState {

    private String airportName;
    private List<String> flightNumbers;
    private List<Flight> flights; // To store the full flight data
    private String errorMessage;

    // Constructor for success with flight data
    public SearchByAirlineIDState(String airportName, List<String> flightNumbers, List<Flight> flights) {
        this.airportName = airportName;
        this.flightNumbers = flightNumbers;
        this.flights = flights;
        this.errorMessage = null;
    }

    // Constructor for failure with an error message
    public SearchByAirlineIDState(String errorMessage) {
        this.airportName = null;
        this.flightNumbers = null;
        this.flights = null;
        this.errorMessage = errorMessage;
    }

    public String getAirportName() {
        return airportName;
    }

    public List<String> getFlightNumbers() {
        return flightNumbers;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccessful() {
        return errorMessage == null;
    }
}
