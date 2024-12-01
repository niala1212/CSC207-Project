package adapters;

/**
 * The Abstract state with flight details.
 */
public abstract class AbstractState {
    private String searchError;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String arrivalAirport;
    private String departureAirport;
    private String status;

    // Getters for UI access
    public final String getSearchError() {
        return searchError;
    }

    public final String getFlightNumber() {
        return flightNumber;
    }

    public final String getDepartureTime() {
        return departureTime;
    }

    public final String getArrivalTime() {
        return arrivalTime;
    }

    public final String getArrivalAirport() {
        return arrivalAirport;
    }

    public final String getDepartureAirport() {
        return departureAirport;
    }

    public final String getStatus() {
        return status;
    }

    // Setters for UI access
    public final void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public final void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public final void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public final void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public final void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public final void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public final void setStatus(String status) {
        this.status = status;
    }
}
