package entities;
import java.time.LocalDateTime;

public class Flight {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime estimatedDepartureTime;
    private String currentLocation;

    public void setFlightNumber(String FLightNumber) {
        this.flightNumber = FLightNumber;
    }

    public void setAirline(String Airline) {
        this.airline = Airline;
    }

    public void setDepartureAirport(String string) {
        this.departureAirport = string;
    }

    public void setArrivalAirport(String string) {
        this.arrivalAirport = string;
    }

    public void setStatus(String flightStatus) {
        this.status = flightStatus;
    }

    public void setScheduledDepartureTime(LocalDateTime parse) {
        this.scheduledDepartureTime = parse;
    }

    public void setEstimatedDepartureTime(LocalDateTime parse) {
        this.estimatedDepartureTime = parse;
    }

    public void setScheduledArrivalTime(LocalDateTime parse) {
        this.scheduledArrivalTime = parse;
    }

    public void setEstimatedArrivalTime(LocalDateTime parse) {
        this.estimatedArrivalTime = parse;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getStatus() {
        return status;
    }

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime.toString();
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime.toString();
    }

    public String getScheduledArrivalTime() {
        return scheduledArrivalTime.toString();
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime.toString();
    }

    // Constructor, getters, and setters
    // Additional methods
}