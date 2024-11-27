package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    // Test case for the default constructor of the Airport class
    @Test
    void testDefaultConstructor() {
        // Creating an Airport object using the default constructor
        Airport airport = new Airport();

        // Assert that the default airport code is null
        assertNull(airport.getairportCode());

        // Assert that the default airport name is null
        assertNull(airport.getName());

        // Assert that the default airport location is null
        assertNull(airport.getLocation());
    }

    // Test case for the parameterized constructor of the Airport class
    @Test
    void testParameterizedConstructor() {
        // Defining the location coordinates for the airport
        double[] location = {14.5547, 121.0197};

        // Creating an Airport object with a specific airport code, name, and location
        Airport airport = new Airport("MNL", "Ninoy Aquino International", location);

        // Assert that the airport code is correctly set to "MNL"
        assertEquals("MNL", airport.getairportCode());

        // Assert that the airport name is correctly set to "Ninoy Aquino International"
        assertEquals("Ninoy Aquino International", airport.getName());

        // Assert that the airport location is correctly set to the given coordinates
        assertArrayEquals(location, airport.getLocation());
    }

    // Test case for the setter method setairportCode()
    @Test
    void testSetAirportCode() {
        // Creating an Airport object using the default constructor
        Airport airport = new Airport();

        // Setting the airport code to "JFK"
        airport.setairportCode("JFK");

        // Assert that the airport code is correctly set to "JFK"
        assertEquals("JFK", airport.getairportCode());
    }

    // Test case for the setter method setName()
    @Test
    void testSetName() {
        // Creating an Airport object using the default constructor
        Airport airport = new Airport();

        // Setting the airport name to "John F. Kennedy International Airport"
        airport.setName("John F. Kennedy International Airport");

        // Assert that the airport name is correctly set to "John F. Kennedy International Airport"
        assertEquals("John F. Kennedy International Airport", airport.getName());
    }

    // Test case for the setter method setLocation()
    @Test
    void testSetLocation() {
        // Creating an Airport object using the default constructor
        Airport airport = new Airport();

        // Defining the location coordinates for the airport
        double[] location = {40.6413, -73.7781};

        // Setting the airport location to the given coordinates
        airport.setLocation(location);

        // Assert that the airport location is correctly set to the given coordinates
        assertArrayEquals(location, airport.getLocation());
    }

    // Test case for the toString() method of the Airport class
    @Test
    void testToString() {
        // Defining the location coordinates for the airport
        double[] location = {40.6413, -73.7781};

        // Creating an Airport object with a specific airport code, name, and location
        Airport airport = new Airport("JFK", "John F. Kennedy International Airport", location);

        // Assert that the toString method returns the correct string representation
        assertEquals(
                "Airport{Airport Code = 'JFK', Name = 'John F. Kennedy International Airport'," +
                        " Location = [40.6413, -73.7781]}", airport.toString());
    }
}
