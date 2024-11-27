package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {

    // Test case for the default constructor of the Airline class
    @Test
    void testDefaultConstructor() {
        // Creating an Airline object using the default constructor
        Airline airline = new Airline();

        // Assert that the default airline code is null
        assertNull(airline.getAirlineCode());

        // Assert that the default airline name is null
        assertNull(airline.getName());
    }

    // Test case for the parameterized constructor of the Airline class
    @Test
    void testParameterizedConstructor() {
        // Creating an Airline object with a specific airline code and name
        Airline airline = new Airline("AA", "American Airlines");

        // Assert that the airline code is correctly set to "AA"
        assertEquals("AA", airline.getAirlineCode());

        // Assert that the airline name is correctly set to "American Airlines"
        assertEquals("American Airlines", airline.getName());
    }

    // Test case for the setter method setAirlineCode()
    @Test
    void testSetAirlineCode() {
        // Creating an Airline object using the default constructor
        Airline airline = new Airline();

        // Setting the airline code to "DL"
        airline.setAirlineCode("DL");

        // Assert that the airline code is correctly set to "DL"
        assertEquals("DL", airline.getAirlineCode());
    }

    // Test case for the setter method setName()
    @Test
    void testSetName() {
        // Creating an Airline object using the default constructor
        Airline airline = new Airline();

        // Setting the airline name to "Delta Airlines"
        airline.setName("Delta Airlines");

        // Assert that the airline name is correctly set to "Delta Airlines"
        assertEquals("Delta Airlines", airline.getName());
    }

    // Test case for the toString() method of the Airline class
    @Test
    void testToString() {
        // Creating an Airline object with a specific airline code and name
        Airline airline = new Airline("UA", "United Airlines");

        // Assert that the toString method returns the correct string representation
        assertEquals("UA{, Name ='United Airlines'}", airline.toString());
    }
}
