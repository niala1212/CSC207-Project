package adapters.SearchByAirlineID;

import entities.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Search By Airline ID View Model.
 */
public class SearchByAirlineIDState {

    private String airlineName = "";
    private List<String> flightNumbers = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>(); // To store the full flight data
    private String errorMessage = null;

    //Getters
    public String getAirlineName() {
        return airlineName;
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


    // Setters
    public void setAirlineName(String airlineName){ this.airlineName = airlineName; }

    public void setFlightNumbers(List<String> flightNumbers) { this.flightNumbers = flightNumbers;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    //To String
    public String getFlightDetailsString(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.toString(); // Format this string as needed
            }
        }
        return null; // Return null if no flight is found
    }

    // Success Checker
    public boolean isSuccessful() {
        return errorMessage == null;
    }
}
