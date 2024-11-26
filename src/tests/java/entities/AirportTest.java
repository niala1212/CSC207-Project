package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Test
    void testDefaultConstructor() {
        Airport airport = new Airport();
        assertNull(airport.getairportCode());
        assertNull(airport.getName());
        assertNull(airport.getLocation());
    }

    @Test
    void testParameterizedConstructor() {
        double[] location = {14.5547, 121.0197};
        Airport airport = new Airport("MNL", "Ninoy Aquino International", location);

        assertEquals("MNL", airport.getairportCode());
        assertEquals("Ninoy Aquino International", airport.getName());
        assertArrayEquals(location, airport.getLocation());
    }

    @Test
    void testSetAirportCode() {
        Airport airport = new Airport();
        airport.setairportCode("JFK");

        assertEquals("JFK", airport.getairportCode());
    }

    @Test
    void testSetName() {
        Airport airport = new Airport();
        airport.setName("John F. Kennedy International Airport");

        assertEquals("John F. Kennedy International Airport", airport.getName());
    }

    @Test
    void testSetLocation() {
        Airport airport = new Airport();
        double[] location = {40.6413, -73.7781};
        airport.setLocation(location);

        assertArrayEquals(location, airport.getLocation());
    }

    @Test
    void testToString() {
        double[] location = {40.6413, -73.7781};
        Airport airport = new Airport("JFK", "John F. Kennedy International Airport", location);

        assertEquals("Airport{Airport Code = 'JFK', Name = 'John F. Kennedy International Airport', Location = [40.6413, -73.7781]}", airport.toString());
    }
}
