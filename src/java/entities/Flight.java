package entities;

import java.util.Arrays;

/**
 * The representation of a flight in our program.
 */
public class Flight {
    private final String flightNumber;
    private final String flightDate;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String status;
    private String scheduledArrivalTime;
    private String scheduledDepartureTime;
    private String estimatedArrivalTime;
    private String estimatedDepartureTime;
    //    ALL TIMES ARE GIVEN IN UTC
    private double[] currentLocation = null;

    public Flight(String flightNUmber, String flightDate) {
        this.flightNumber = flightNUmber;
        this.flightDate = flightDate;
    }

    public void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }

    //Setters
    public void setAirline(String airline) {
        this.airline = airline;
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

    public void setScheduledDepartureTime(String parse) {
        this.scheduledDepartureTime = parse  + " UTC";
    }

    public void setEstimatedDepartureTime(String parse) {
        this.estimatedDepartureTime = parse  + " UTC";
    }

    public void setScheduledArrivalTime(String parse) {
        this.scheduledArrivalTime = parse  + " UTC";
    }

    public void setEstimatedArrivalTime(String parse) {
        this.estimatedArrivalTime = parse  + " UTC";
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

    public String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public String getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public double[] getCoordinates() {
        return currentLocation;
    }

    public String getFlightDate() {
        return flightDate;
    }

    // string method
    @Override
    public String toString() {
        return "Flight Information:\n\n" +
                "  Flight Number = '" + flightNumber + "',\n" +
                "  Flight Date = '" + flightDate + "',\n" +
                "  Airline = " + (airline != null ? airline : "N/A") + ",\n" +
                "  Departure Airport = " + (departureAirport != null ? departureAirport : "N/A") + ",\n" +
                "  Arrival Airport = " + (arrivalAirport != null ? arrivalAirport : "N/A") + ",\n" +
                "  Status = '" + status + "',\n" +
                "  Scheduled DepartureTime = '" + scheduledDepartureTime + "',\n" +
                "  Scheduled ArrivalTime = '" + scheduledArrivalTime + "',\n" +
                "  Estimated Departure Time = '" + estimatedDepartureTime + "',\n" +
                "  Estimated Arrival Time = '" + estimatedArrivalTime + "',\n" +
                "  Current Location = " + (currentLocation != null && currentLocation.length >= 2
                ? "[" + currentLocation[0] + ", " + currentLocation[1] + "]"
                : "N/A") + "\n";
    }
}