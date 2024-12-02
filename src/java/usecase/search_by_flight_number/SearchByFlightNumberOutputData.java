package usecase.search_by_flight_number;

import entities.Flight;

/**
 * Output Data for the SearchByFlightNumber Use Case.
 */
public class SearchByFlightNumberOutputData {

    private final Flight filteredFlight;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchByFlightNumberOutputData(Flight filteredFlight) {
        this.filteredFlight = filteredFlight;
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchByFlightNumberOutputData(String errorMessage) {
        this.filteredFlight = null;
        this.errorMessage = errorMessage;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    // Getters with null checks
    public final String getFlightNumber() {
        return (filteredFlight != null) ? filteredFlight.getFlightNumber() : null;
    }

    public final String getDepartureTime() {
        return (filteredFlight != null) ? filteredFlight.getScheduledDepartureTime() : null;
    }

    public final String getArrivalTime() {
        return (filteredFlight != null) ? filteredFlight.getScheduledArrivalTime() : null;
    }

    public String getDepartureAirport() {
        return (filteredFlight != null) ? filteredFlight.getDepartureAirport() : null;
    }

    public String getArrivalAirport() {
        return (filteredFlight != null) ? filteredFlight.getArrivalAirport() : null;
    }

    public final String getStatus() {
        return (filteredFlight != null) ? filteredFlight.getStatus() : null;
    }
}
