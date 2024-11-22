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
    private double[] currentLocation;

    public Flight(String flightNUmber, String flightDate) {
        this.flightNumber = flightNUmber;
        this.flightDate = flightDate;
    }

    public void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }


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

    public String getCoordinates() {
        return Arrays.toString(currentLocation);
    }

    public String getFlightDate() {
        return flightDate;
    }

    // string method
    @Override
    public String toString() {
        return "Flight Information:\n" +
                "  flightNumber= '" + flightNumber + "',\n" +
                "  flightDate= '" + flightDate + "',\n" +
                "  airline= " + (airline != null ? airline : "N/A") + ",\n" +
                "  departureAirport= " + (departureAirport != null ? departureAirport : "N/A") + ",\n" +
                "  arrivalAirport= " + (arrivalAirport != null ? arrivalAirport : "N/A") + ",\n" +
                "  status= '" + status + "',\n" +
                "  scheduledDepartureTime= '" + scheduledDepartureTime + "',\n" +
                "  scheduledArrivalTime= '" + scheduledArrivalTime + "',\n" +
                "  estimatedDepartureTime= '" + estimatedDepartureTime + "',\n" +
                "  estimatedArrivalTime= '" + estimatedArrivalTime + "',\n" +
                "  currentLocation= " + (currentLocation != null && currentLocation.length >= 2
                ? "[" + currentLocation[0] + ", " + currentLocation[1] + "]"
                : "N/A") + "\n";
    }
}