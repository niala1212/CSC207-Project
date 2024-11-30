package usecase.search_by_flight_number;

import entities.Flight;

/**
 * Output Data for the SearchByAirline Use Case.
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

    public final String getFlightNumber() {
        return filteredFlight.getFlightNumber();
    }

    public final String getDepartureTime() {
        return filteredFlight.getScheduledDepartureTime();
    }

    public final String getArrivalTime() {
        return filteredFlight.getScheduledArrivalTime();
    }

    public final String getStatus() {
        return filteredFlight.getStatus();
    }
}
