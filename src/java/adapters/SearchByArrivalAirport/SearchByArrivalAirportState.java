package adapters.SearchByArrivalAirport;

import entities.Flight;

import java.util.ArrayList;
import java.util.List;

public class SearchByArrivalAirportState {

    private String airportName = "";
    private List<String> flightNumbers = new ArrayList<>();
    private List<Flight> flights = new ArrayList<>();  // Full flight data
    private String message = null;

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

    //To String
    public String getFlightDetailsString(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.toString(); // Format this string as needed
            }
        }
        return null; // Return null if no flight is found
    }



    public boolean isSuccessful() {
        return message == null;
    }


}
