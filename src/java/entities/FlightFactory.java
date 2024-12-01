package entities;

import java.util.List;

/**
 * Factory for creating Flight objects.
 */

public class FlightFactory {

    /**
     * The updated flight factory that works with the api calls.
     * @param flightInfo given as an array with the following format:
     *             [flightNumber, flightDate, Airline, Dep_Airport,
     *         Arr_Airport, Status, departureScheduled, departureEstimated,
     *         arrivalScheduled, arrivalEstimated]
     * @param coordinates given as <latitude, longitude>
     * @return Populated flight class
     */

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:SuppressWarnings"})
    public static Flight create(List<String> flightInfo, double[] coordinates) {
        Flight flight = new Flight(flightInfo.get(0), flightInfo.get(1));

        if (coordinates != null) {
            flight.setCurrentLocation(coordinates);
        }

        flight.setAirline(flightInfo.get(2));
        flight.setDepartureAirport(flightInfo.get(3));
        flight.setArrivalAirport(flightInfo.get(4));
        flight.setStatus(flightInfo.get(5));
        flight.setScheduledDepartureTime(flightInfo.get(6));
        flight.setEstimatedDepartureTime(flightInfo.get(7));
        flight.setScheduledArrivalTime(flightInfo.get(8));
        flight.setEstimatedArrivalTime(flightInfo.get(9));

        return flight;
    }
}