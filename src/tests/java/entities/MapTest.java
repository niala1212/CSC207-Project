package entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    // Test case for the default constructor of the Map class
    @Test
    void testDefaultConstructor() {
        // Creating a Map object using the default constructor
        Map map = new Map();

        // Assert that the default Map ID is 0
        assertEquals(0, map.getMapID());

        // Assert that the default flight list is null
        assertNull(map.getFlightList());

        // Assert that the default zoom level is 0
        assertEquals(0, map.getZoomLevel());

        // Assert that the default centre coordinates are null
        assertNull(map.getCentreCoordinates());
    }

    // Test case for the parameterized constructor of the Map class
    @Test
    void testParameterizedConstructor() {
        // Creating a list of flights to pass to the constructor
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AB123", "2024-11-26"));

        // Defining the coordinates for the map's centre
        double[] coordinates = {40.7128, -74.0060};

        // Creating a Map object with the parameterized constructor
        Map map = new Map(1, flights, 5, coordinates);

        // Assert that the Map ID is correctly set to 1
        assertEquals(1, map.getMapID());

        // Assert that the flight list is correctly set
        assertEquals(flights, map.getFlightList());

        // Assert that the zoom level is correctly set to 5
        assertEquals(5, map.getZoomLevel());

        // Assert that the centre coordinates are correctly set
        assertArrayEquals(coordinates, map.getCentreCoordinates());
    }

    // Test case for adding flights to the map's flight list
    @Test
    void testAddFlight() {
        // Creating a Map object with an empty flight list
        List<Flight> flights = new ArrayList<>();
        Map map = new Map(1, flights, 5, new double[]{40.7128, -74.0060});

        // Creating new Flight objects
        Flight flight1 = new Flight("AB123", "2024-11-26");
        Flight flight2 = new Flight("CD456", "2024-11-27");
        Flight duplicateFlight = new Flight("AB123", "2024-11-26");

        // Adding flights to the map
        map.addFlight(flight1);
        map.addFlight(flight2);
        map.addFlight(duplicateFlight); // Duplicate flight should not be added

        // Assert that only 2 flights are added
        assertEquals(2, map.getFlightList().size());

        // Assert that the flight list contains flight1 and flight2
        assertTrue(map.getFlightList().contains(flight1));
        assertTrue(map.getFlightList().contains(flight2));
    }

    // Test case for adding a flight when the flight list is null
    @Test
    void testAddFlightWithNullFlightList() {
        // Creating a Map object with a null flight list
        Map map = new Map();
        Flight flight = new Flight("AB123", "2024-11-26");

        // No exception should be thrown, but flight list should still be null
        map.addFlight(flight);
        assertNull(map.getFlightList());
    }

    // Test case for updating the centre coordinates of the map
    @Test
    void testUpdateCentreCoordinates() {
        // Creating a Map object using the default constructor
        Map map = new Map();

        // Defining new coordinates for the map's centre
        double[] coordinates = {40.7128, -74.0060};

        // Updating the centre coordinates of the map
        map.updateCentreCoordinates(coordinates);

        // Assert that the centre coordinates are updated correctly
        assertArrayEquals(coordinates, map.getCentreCoordinates());
    }

    // Test case for updating the zoom level of the map
    @Test
    void testUpdateZoomLevel() {
        // Creating a Map object using the default constructor
        Map map = new Map();

        // Updating the zoom level to 10
        map.updateZoomLevel(10);

        // Assert that the zoom level is updated correctly
        assertEquals(10, map.getZoomLevel());
    }

    // Test case for updating the centre coordinates with a null value
    @Test
    void testUpdateCentreCoordinatesWithNull() {
        // Creating a Map object using the default constructor
        Map map = new Map();

        // Updating the centre coordinates to null
        map.updateCentreCoordinates(null);

        // Assert that the centre coordinates are set to null
        assertNull(map.getCentreCoordinates());
    }

    // Test case for adding a null flight to the map's flight list
    @Test
    void testAddFlightWithNullFlight() {
        // Creating a Map object with an empty flight list
        List<Flight> flights = new ArrayList<>();
        Map map = new Map(1, flights, 5, new double[]{40.7128, -74.0060});

        // Adding a null flight should not increase the flight list size
        map.addFlight(null);
        assertEquals(0, map.getFlightList().size());
    }
}
