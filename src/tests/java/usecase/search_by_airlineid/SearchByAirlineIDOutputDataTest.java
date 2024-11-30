package usecase.search_by_airlineid;

import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class SearchByAirlineIDOutputDataTest {

    private List<Flight> flights;
    private SearchByAirlineIDOutputData successOutputData;
    private SearchByAirlineIDOutputData failureOutputData;

    @Before
    public void setUp() {
        // Set up mock data for flights
        flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27")); // Assuming Flight is a valid entity

        // Create SearchByAirlineIDOutputData instances
        successOutputData = new SearchByAirlineIDOutputData(flights);
        failureOutputData = new SearchByAirlineIDOutputData("No flights found for the specified airline.");
    }

    @Test
    public void testSuccessConstructor() {
        // Test success constructor (with filtered flights)
        assertNotNull("Filtered flights should not be null", successOutputData.getFilteredFlights());
        assertNull("Error message should be null in success case", successOutputData.getErrorMessage());
        assertEquals("The filtered flights list should match the input", flights,
                successOutputData.getFilteredFlights());
    }

    @Test
    public void testFailureConstructor() {
        // Test failure constructor (with an error message)
        assertNull("Filtered flights should be null in failure case", failureOutputData.getFilteredFlights());
        assertNotNull("Error message should not be null in failure case", failureOutputData.getErrorMessage());
        assertEquals("The error message should match the input",
                "No flights found for the specified airline.", failureOutputData.getErrorMessage());
    }

    @Test
    public void testIsUseCaseFailed_Success() {
        // Test isUseCaseFailed method for a success case (no error message)
        assertFalse("Use case should not be failed for success", successOutputData.isUseCaseFailed());
    }

    @Test
    public void testIsUseCaseFailed_Failure() {
        // Test isUseCaseFailed method for a failure case (error message exists)
        assertTrue("Use case should be failed for failure", failureOutputData.isUseCaseFailed());
    }

    @Test
    public void testGetFilteredFlightsWhenFailure() {
        // Verify that getFilteredFlights() returns null in failure case
        assertNull("Filtered flights should be null in failure case", failureOutputData.getFilteredFlights());
    }

    @Test
    public void testGetErrorMessageWhenSuccess() {
        // Verify that getErrorMessage() returns null in success case
        assertNull("Error message should be null in success case", successOutputData.getErrorMessage());
    }
    @Test
    public void testGetFilteredFlightsWhenSuccess() {
        // Verify that getFilteredFlights() returns the correct list in success case
        assertNotNull("Filtered flights should not be null in success case", successOutputData.getFilteredFlights());
        assertEquals("The filtered flights should match the expected list", flights,
                successOutputData.getFilteredFlights());
    }

    @Test
    public void testGetErrorMessageWhenFailure() {
        // Verify that getErrorMessage() returns the correct message in failure case
        assertNotNull("Error message should not be null in failure case", failureOutputData.getErrorMessage());
        assertEquals("The error message should match the input",
                "No flights found for the specified airline.", failureOutputData.getErrorMessage());
    }
}
