package entities;

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

    private final String utc = " UTC";

    public Flight(String flightNumber, String flightDate) {
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
    }

    public final void setCurrentLocation(double[] currentLocation) {
        this.currentLocation = currentLocation;
    }
    // Setters

    public final void setAirline(String airline) {
        this.airline = airline;
    }

    public final void setDepartureAirport(String string) {
        this.departureAirport = string;
    }

    public final void setArrivalAirport(String string) {
        this.arrivalAirport = string;
    }

    public final void setStatus(String flightStatus) {
        this.status = flightStatus;
    }

    public final void setScheduledDepartureTime(String parse) {
        this.scheduledDepartureTime = parse + utc;
    }

    public final void setEstimatedDepartureTime(String parse) {
        this.estimatedDepartureTime = parse + utc;
    }

    public final void setScheduledArrivalTime(String parse) {
        this.scheduledArrivalTime = parse + utc;
    }

    public final void setEstimatedArrivalTime(String parse) {
        this.estimatedArrivalTime = parse + utc;
    }

    // Getters
    public final String getFlightNumber() {
        return flightNumber;
    }

    public final String getAirline() {
        return airline;
    }

    public final String getDepartureAirport() {
        return departureAirport;
    }

    public final String getArrivalAirport() {
        return arrivalAirport;
    }

    public final String getStatus() {
        return status;
    }

    public final String getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public final String getEstimatedDepartureTime() {
        return estimatedDepartureTime;
    }

    public final String getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public final String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public final double[] getCoordinates() {
        return currentLocation;
    }

    public final String getFlightDate() {
        return flightDate;
    }

    // string method
    @SuppressWarnings({"checkstyle:AvoidInlineConditionals", "checkstyle:SuppressWarnings",
        "checkstyle:MultipleStringLiterals"})
    @Override
    public String toString() {
        return "Flight Information:\n\n"
                + "  Flight Number = '" + flightNumber + "',\n"
                + "  Flight Date = '" + flightDate + "',\n"
                + "  Airline = " + (airline != null ? airline : "N/A") + ",\n"
                + "  Departure Airport = " + (departureAirport != null ? departureAirport : "N/A") + ",\n"
                + "  Arrival Airport = " + (arrivalAirport != null ? arrivalAirport : "N/A") + ",\n"
                + "  Status = '" + status + "',\n"
                + "  Scheduled DepartureTime = '" + scheduledDepartureTime + "',\n"
                + "  Scheduled ArrivalTime = '" + scheduledArrivalTime + "',\n"
                + "  Estimated Departure Time = '" + estimatedDepartureTime + "',\n"
                + "  Estimated Arrival Time = '" + estimatedArrivalTime + "',\n"
                + "  Current Location = " + (currentLocation != null && currentLocation.length >= 2
                ? "[" + currentLocation[0] + ", " + currentLocation[1] + "]"
                : "N/A") + "\n";
    }
}
