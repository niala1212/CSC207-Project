package entities;
import java.time.LocalDateTime;

/**
 * The representation of a flight in our program.
 */

public class Flight {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime actualDepartureTime;
    private String currentLocation;

    // Constructor
    public Flight(String flightNumber, String airline, String departureAirport, String arrivalAirport,
                  String status, LocalDateTime scheduledArrivalTime, LocalDateTime scheduledDepartureTime,
                  LocalDateTime estimatedArrivalTime, LocalDateTime actualDepartureTime, String currentLocation) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.status = status;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.currentLocation = currentLocation;
    }

    // Getters
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

    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public LocalDateTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public LocalDateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
}
