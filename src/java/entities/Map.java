package entities;

import java.util.List;

/**
 * The representation of a map in our program.
 */
public class Map {
    private List<Flight> flightList;

    // Constructor
    public Map(List<Flight> flightList) {
        this.flightList = flightList;
    }

    // Getters
    public final List<Flight> getFlightList() {
        return flightList;
    }

    // Setters
    //    public void addFlight(Flight flight) {
    //        if (flightList != null && flight != null) {
    //            for (Flight existingFlight : flightList) {
    //                if (existingFlight.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
    //                    return; // Flight is already in the list, so we skip adding
    //                }
    //            }
    //            flightList.add(flight); // No duplicate found, so add the flight
    //        }
    //    }

}
