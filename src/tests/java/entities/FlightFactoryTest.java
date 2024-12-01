package entities;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightFactoryTest {

    // Test case for creating a Flight object using the FlightFactory
    @Test
    void testCreateFlight() {
        // Sample flight information as a list of strings
        List<String> flightInfo = List.of(
                "AB123", "2024-11-26", "AirlineName", "JFK", "LAX", "On Time",
                "12:00", "12:10", "15:00", "15:10"
        );

        // Coordinates for the flight's location (in this case, coordinates for New York City)
        double[] coordinates = {40.7128, -74.0060};

        // Creating a Flight object using the FlightFactory's create method
        Flight flight = FlightFactory.create(flightInfo, coordinates);

        // Assert that the flight number is correctly set to "AB123"
        assertEquals("AB123", flight.getFlightNumber());

        // Assert that the flight date is correctly set to "2024-11-26"
        assertEquals("2024-11-26", flight.getFlightDate());

        // Assert that the airline name is correctly set to "AirlineName"
        assertEquals("AirlineName", flight.getAirline());

        // Assert that the departure airport is correctly set to "JFK"
        assertEquals("JFK", flight.getDepartureAirport());

        // Assert that the arrival airport is correctly set to "LAX"
        assertEquals("LAX", flight.getArrivalAirport());

        // Assert that the flight status is correctly set to "On Time"
        assertEquals("On Time", flight.getStatus());

        // Assert that the scheduled departure time is correctly set to "12:00 UTC"
        assertEquals("12:00 UTC", flight.getScheduledDepartureTime());

        // Assert that the estimated departure time is correctly set to "12:10 UTC"
        assertEquals("12:10 UTC", flight.getEstimatedDepartureTime());

        // Assert that the scheduled arrival time is correctly set to "15:00 UTC"
        assertEquals("15:00 UTC", flight.getScheduledArrivalTime());

        // Assert that the estimated arrival time is correctly set to "15:10 UTC"
        assertEquals("15:10 UTC", flight.getEstimatedArrivalTime());

        // Assert that the coordinates are correctly set to "[40.7128, -74.006]"
        assertArrayEquals(new double[]{40.7128, -74.006}, flight.getCoordinates());
    }

    // Test case for creating a Flight object with null coordinates
    @Test
    void testCreateFlightWithNullCoordinates() {
        // Sample flight information as a list of strings
        List<String> flightInfo = List.of(
                "AB123", "2024-11-26", "AirlineName", "JFK", "LAX", "On Time",
                "12:00", "12:10", "15:00", "15:10"
        );

        // Null coordinates for the flight (i.e., no location information)
        double[] coordinates = null;

        // Creating a Flight object using the FlightFactory's create method with null coordinates
        Flight flight = FlightFactory.create(flightInfo, coordinates);

        // Assert that the flight's coordinates are null since no coordinates were provided
        assertNull(flight.getCoordinates());
    }
}
