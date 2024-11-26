package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {

    @Test
    void testDefaultConstructor() {
        Airline airline = new Airline();
        assertNull(airline.getAirlineCode());
        assertNull(airline.getName());
    }

    @Test
    void testParameterizedConstructor() {
        Airline airline = new Airline("AA", "American Airlines");

        assertEquals("AA", airline.getAirlineCode());
        assertEquals("American Airlines", airline.getName());
    }

    @Test
    void testSetAirlineCode() {
        Airline airline = new Airline();
        airline.setAirlineCode("DL");

        assertEquals("DL", airline.getAirlineCode());
    }

    @Test
    void testSetName() {
        Airline airline = new Airline();
        airline.setName("Delta Airlines");

        assertEquals("Delta Airlines", airline.getName());
    }

    @Test
    void testToString() {
        Airline airline = new Airline("UA", "United Airlines");

        assertEquals("UA{, Name ='United Airlines'}", airline.toString());
    }
}
