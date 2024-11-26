package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testConstructor() {
        Flight flight = new Flight("AB123", "2024-11-26");
        assertEquals("AB123", flight.getFlightNumber());
        assertEquals("2024-11-26", flight.getFlightDate());
    }

    @Test
    void testSettersAndGetters() {
        Flight flight = new Flight("AB123", "2024-11-26");
        flight.setAirline("AirlineName");
        flight.setDepartureAirport("JFK");
        flight.setArrivalAirport("LAX");
        flight.setStatus("On Time");
        flight.setScheduledDepartureTime("12:00");
        flight.setScheduledArrivalTime("15:00");
        flight.setEstimatedDepartureTime("12:10");
        flight.setEstimatedArrivalTime("15:10");
        flight.setCurrentLocation(new double[]{40.7128, -74.0060});

        assertEquals("AirlineName", flight.getAirline());
        assertEquals("JFK", flight.getDepartureAirport());
        assertEquals("LAX", flight.getArrivalAirport());
        assertEquals("On Time", flight.getStatus());
        assertEquals("12:00 UTC", flight.getScheduledDepartureTime());
        assertEquals("15:00 UTC", flight.getScheduledArrivalTime());
        assertEquals("12:10 UTC", flight.getEstimatedDepartureTime());
        assertEquals("15:10 UTC", flight.getEstimatedArrivalTime());
        assertEquals("[40.7128, -74.006]", flight.getCoordinates());
    }

    @Test
    void testToString() {
        Flight flight = new Flight("AB123", "2024-11-26");
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
        assertEquals(expected, flight.toString());
    }
}
