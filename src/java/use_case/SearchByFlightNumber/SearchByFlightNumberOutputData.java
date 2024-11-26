package use_case.SearchByFlightNumber;

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

    public String getErrorMessage() { return errorMessage; }

    public String getFlightNumber() {
        return filteredFlight.getFlightNumber();
    }

    public String getDepartureTime() { return filteredFlight.getScheduledDepartureTime(); }

    public String getArrivalTime() { return filteredFlight.getScheduledArrivalTime(); }

    public String getStatus() { return filteredFlight.getStatus(); }
}
