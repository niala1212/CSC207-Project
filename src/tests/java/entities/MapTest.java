package entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testDefaultConstructor() {
        Map map = new Map();
        assertEquals(0, map.getMapID());
        assertNull(map.getFlightList());
        assertEquals(0, map.getZoomLevel());
        assertNull(map.getCentreCoordinates());
    }

    @Test
    void testParameterizedConstructor() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AB123", "2024-11-26"));
        double[] coordinates = {40.7128, -74.0060};

        Map map = new Map(1, flights, 5, coordinates);

        assertEquals(1, map.getMapID());
        assertEquals(flights, map.getFlightList());
        assertEquals(5, map.getZoomLevel());
        assertArrayEquals(coordinates, map.getCentreCoordinates());
    }

    @Test
    void testAddFlight() {
        List<Flight> flights = new ArrayList<>();
        Map map = new Map(1, flights, 5, new double[]{40.7128, -74.0060});

        Flight flight1 = new Flight("AB123", "2024-11-26");
        Flight flight2 = new Flight("CD456", "2024-11-27");
        Flight duplicateFlight = new Flight("AB123", "2024-11-26");

        map.addFlight(flight1);
        map.addFlight(flight2);
        map.addFlight(duplicateFlight); // This should not be added

        assertEquals(2, map.getFlightList().size());
        assertTrue(map.getFlightList().contains(flight1));
        assertTrue(map.getFlightList().contains(flight2));
    }

    @Test
    void testAddFlightWithNullFlightList() {
        Map map = new Map();
        Flight flight = new Flight("AB123", "2024-11-26");

        // No exception should be thrown, but nothing should be added
        map.addFlight(flight);
        assertNull(map.getFlightList());
    }

    @Test
    void testUpdateCentreCoordinates() {
        Map map = new Map();
        double[] coordinates = {40.7128, -74.0060};

        map.updateCentreCoordinates(coordinates);

        assertArrayEquals(coordinates, map.getCentreCoordinates());
    }

    @Test
    void testUpdateZoomLevel() {
        Map map = new Map();
        map.updateZoomLevel(10);

        assertEquals(10, map.getZoomLevel());
    }

    @Test
    void testUpdateCentreCoordinatesWithNull() {
        Map map = new Map();
        map.updateCentreCoordinates(null);

        assertNull(map.getCentreCoordinates());
    }

    @Test
    void testAddFlightWithNullFlight() {
        List<Flight> flights = new ArrayList<>();
        Map map = new Map(1, flights, 5, new double[]{40.7128, -74.0060});

        // Adding null should not increase the flight list size
        map.addFlight(null);
        assertEquals(0, map.getFlightList().size());
    }
}
