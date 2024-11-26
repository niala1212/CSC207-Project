package adapters.SearchByAirlineID;

import entities.Flight;

import java.util.ArrayList;
import java.util.List;

public class SearchByAirlineIDState {

    private String airlineName = "";
    private List<String> flightNumbers = new ArrayList<String>();
    private List<Flight> flights = new ArrayList<Flight>(); // To store the full flight data
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


    //Success Checker
    public boolean isSuccessful() {
        return errorMessage == null;
    }
}
