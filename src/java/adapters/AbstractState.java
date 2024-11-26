package adapters;

public abstract class AbstractState {
    private String searchError;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String arrivalAirport;
    private String departureAirport;
    private String status;

    public String getSearchError() { return searchError; }

    public String getFlightNumber() { return flightNumber; }

    public String getDepartureTime() {return departureTime;}

    public String getArrivalTime() {return arrivalTime;}

    public String getArrivalAirport() { return arrivalAirport; }

    public String getDepartureAirport() { return departureAirport; }

    public String getStatus() {return status;}

    public void setSearchError(String searchError) { this.searchError = searchError; }

    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}

    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public void setArrivalAirport(String arrivalAirport) { this.arrivalAirport = arrivalAirport; }

    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }

    public void setStatus(String status) {this.status = status;}
}
