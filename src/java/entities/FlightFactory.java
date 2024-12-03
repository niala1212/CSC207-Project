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
     * @param coordinates given as (latitude, longitude).
     * @return Populated flight class
     */

    public static Flight create(List<String> flightInfo, double[] coordinates) {
        // Constants for indices in flightInfo
        final int flightNumberIndex = 0;
        final int flightCodeIndex = 1;
        final int airlineIndex = 2;
        final int departureAirportIndex = 3;
        final int arrivalAirportIndex = 4;
        final int statusIndex = 5;
        final int scheduledDepartureIndex = 6;
        final int estimatedDepartureIndex = 7;
        final int scheduledArrivalIndex = 8;
        final int estimatedArrivalIndex = 9;

        Flight flight = new Flight(flightInfo.get(flightNumberIndex), flightInfo.get(flightCodeIndex));

        if (coordinates != null) {
            flight.setCurrentLocation(coordinates);
        }

        flight.setAirline(flightInfo.get(airlineIndex));
        flight.setDepartureAirport(flightInfo.get(departureAirportIndex));
        flight.setArrivalAirport(flightInfo.get(arrivalAirportIndex));
        flight.setStatus(flightInfo.get(statusIndex));
        flight.setScheduledDepartureTime(flightInfo.get(scheduledDepartureIndex));
        flight.setEstimatedDepartureTime(flightInfo.get(estimatedDepartureIndex));
        flight.setScheduledArrivalTime(flightInfo.get(scheduledArrivalIndex));
        flight.setEstimatedArrivalTime(flightInfo.get(estimatedArrivalIndex));

        return flight;
    }
}
