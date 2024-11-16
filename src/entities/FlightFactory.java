package entities;

import data_access.Call_API;

/**
 * Factory for creating Flight objects.
 */

public class FlightFactory {

    public Flight create(String flightNumber, String departureDate){
        Flight flight = new Flight(flightNumber, departureDate);
        Call_API.callAPI(flight);
        return flight;
    }
}