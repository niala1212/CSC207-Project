package data_access;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Flight;

import java.util.List;

class AllFileCallingTest {

    private All_File_Calling dataAccess;

    @BeforeEach
    void setUp() {
        dataAccess = new All_File_Calling();
    }

    @Test
    void testGetFlightsByAirlineId() {
        List<Flight> flights = dataAccess.getFlightsByAirlineId("AA");
        assertNotNull(flights);
        assertTrue(flights.size() > 0);
        // Add assertions based on expected flight data
    }

    // Additional tests for handling edge cases (e.g., file read failure, empty data, etc.)
}
