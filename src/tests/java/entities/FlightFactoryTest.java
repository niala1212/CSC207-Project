package entities;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightFactoryTest {

    @Test
    void testCreateFlight() {
        List<String> flightInfo = List.of(
                "AB123", "2024-11-26", "AirlineName", "JFK", "LAX", "On Time",
                "12:00", "12:10", "15:00", "15:10"
        );
        double[] coordinates = {40.7128, -74.0060};

        Flight flight = FlightFactory.create(flightInfo, coordinates);

        assertEquals("AB123", flight.getFlightNumber());
        assertEquals("2024-11-26", flight.getFlightDate());
        assertEquals("AirlineName", flight.getAirline());
        assertEquals("JFK", flight.getDepartureAirport());
        assertEquals("LAX", flight.getArrivalAirport());
        assertEquals("On Time", flight.getStatus());
        assertEquals("12:00 UTC", flight.getScheduledDepartureTime());
        assertEquals("12:10 UTC", flight.getEstimatedDepartureTime());
        assertEquals("15:00 UTC", flight.getScheduledArrivalTime());
        assertEquals("15:10 UTC", flight.getEstimatedArrivalTime());
        assertEquals("[40.7128, -74.006]", flight.getCoordinates());
    }

    @Test
    void testCreateFlightWithNullCoordinates() {
        List<String> flightInfo = List.of(
                "AB123", "2024-11-26", "AirlineName", "JFK", "LAX", "On Time",
                "12:00", "12:10", "15:00", "15:10"
        );
        double[] coordinates = null;

        Flight flight = FlightFactory.create(flightInfo, coordinates);

        assertNull(flight.getCoordinates());
    }
}

