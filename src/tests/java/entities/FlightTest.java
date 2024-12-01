package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    // Test case for the constructor of the Flight class
    @Test
    void testConstructor() {
        // Creating a Flight object with a flight number and date
        Flight flight = new Flight("AB123", "2024-11-26");

        // Assert that the flight number is correctly set to "AB123"
        assertEquals("AB123", flight.getFlightNumber());

        // Assert that the flight date is correctly set to "2024-11-26"
        assertEquals("2024-11-26", flight.getFlightDate());
    }

    // Test case for testing setters and getters in the Flight class
    @Test
    void testSettersAndGetters() {
        // Creating a Flight object with a flight number and date
        Flight flight = new Flight("AB123", "2024-11-26");

        // Setting various attributes of the flight
        flight.setAirline("AirlineName");
        flight.setDepartureAirport("JFK");
        flight.setArrivalAirport("LAX");
        flight.setStatus("On Time");
        flight.setScheduledDepartureTime("12:00");
        flight.setScheduledArrivalTime("15:00");
        flight.setEstimatedDepartureTime("12:10");
        flight.setEstimatedArrivalTime("15:10");
        flight.setCurrentLocation(new double[]{40.7128, -74.0060});

        // Assert that the attributes are correctly set using getters
        assertEquals("AirlineName", flight.getAirline());
        assertEquals("JFK", flight.getDepartureAirport());
        assertEquals("LAX", flight.getArrivalAirport());
        assertEquals("On Time", flight.getStatus());
        assertEquals("12:00 UTC", flight.getScheduledDepartureTime());
        assertEquals("15:00 UTC", flight.getScheduledArrivalTime());
        assertEquals("12:10 UTC", flight.getEstimatedDepartureTime());
        assertEquals("15:10 UTC", flight.getEstimatedArrivalTime());
        assertArrayEquals(new double[]{40.7128, -74.006}, flight.getCoordinates());
    }

    // Test case for the toString() method of the Flight class
    @Test
    void testToString() {
        // Creating a Flight object with a flight number and date
        Flight flight = new Flight("AB123", "2024-11-26");

        // Expected string representation of the flight object
        String expected = "Flight Information:\n\n" +
                "  Flight Number = 'AB123',\n" +
                "  Flight Date = '2024-11-26',\n" +
                "  Airline = N/A,\n" +
                "  Departure Airport = N/A,\n" +
                "  Arrival Airport = N/A,\n" +
                "  Status = 'null',\n" +
                "  Scheduled DepartureTime = 'null',\n" +
                "  Scheduled ArrivalTime = 'null',\n" +
                "  Estimated Departure Time = 'null',\n" +
                "  Estimated Arrival Time = 'null',\n" +
                "  Current Location = N/A\n";

        // Assert that the toString() method returns the correct string representation
        assertEquals(expected, flight.toString());
    }
}
