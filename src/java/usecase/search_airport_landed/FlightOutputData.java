package usecase.search_airport_landed;

import entities.Flight;

/**
 * Output Data for a specific flight.
 */
public class FlightOutputData {
    private final Flight flight;

    public FlightOutputData(Flight flight) {
        this.flight = flight;
    }

    // Getters with null checks
    public final Flight getFlight() {
        return flight;
    }

    public final String getFlightNumber() {
        return (flight != null) ? flight.getFlightNumber() : null;
    }

    public final String getDepartureTime() {
        return (flight != null) ? flight.getScheduledDepartureTime() : null;
    }

    public final String getArrivalTime() {
        return (flight != null) ? flight.getScheduledArrivalTime() : null;
    }

    public final String getDepartureAirport() {
        return (flight != null) ? flight.getDepartureAirport() : null;
    }

    public final String getArrivalAirport() {
        return (flight != null) ? flight.getArrivalAirport() : null;
    }

    public final String getStatus() {
        return (flight != null) ? flight.getStatus() : null;
    }

}
