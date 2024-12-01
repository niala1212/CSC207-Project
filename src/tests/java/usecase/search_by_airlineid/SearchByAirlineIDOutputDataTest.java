package usecase.search_by_airlineid;

import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchByAirlineIDOutputDataTest {

    private List<Flight> flights;
    private SearchByAirlineIDOutputData successOutputData;
    private SearchByAirlineIDOutputData failureOutputData;

    @Before
    public void setUp() {
        // Mock data for flights
        flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27")); // Assuming Flight is a valid entity

        // Create SearchByAirlineIDOutputData instances
        successOutputData = new SearchByAirlineIDOutputData(flights);
        failureOutputData = new SearchByAirlineIDOutputData("No flights found for the specified airline.");
    }

    @Test
    public void testSuccessConstructor() {
        assertNotNull("Filtered flights should not be null", successOutputData.getFilteredFlights());
        assertNull("Error message should be null in success case", successOutputData.getErrorMessage());
        assertEquals("The filtered flights list should match the input", flights, successOutputData.getFilteredFlights());
    }

    @Test
    public void testFailureConstructor() {
        assertNull("Filtered flights should be null in failure case", failureOutputData.getFilteredFlights());
        assertNotNull("Error message should not be null in failure case", failureOutputData.getErrorMessage());
        assertEquals("The error message should match the input", "No flights found for the specified airline.",
                failureOutputData.getErrorMessage());
    }

    @Test
    public void testIsUseCaseFailed_Success() {
        assertFalse("Use case should not be failed for success", successOutputData.isUseCaseFailed());
    }

    @Test
    public void testIsUseCaseFailed_Failure() {
        assertTrue("Use case should be failed for failure", failureOutputData.isUseCaseFailed());
    }

    @Test
    public void testGetErrorMessageWhenSuccess() {
        assertNull("Error message should be null in success case", successOutputData.getErrorMessage());
    }

    @Test
    public void testGetFilteredFlightsWhenSuccess() {
        assertNotNull("Filtered flights should not be null in success case", successOutputData.getFilteredFlights());
        assertEquals("The filtered flights should match the expected list", flights, successOutputData.getFilteredFlights());
    }

    @Test
    public void testGetErrorMessageWhenFailure() {
        assertNotNull("Error message should not be null in failure case", failureOutputData.getErrorMessage());
        assertEquals("The error message should match the input", "No flights found for the specified airline.",
                failureOutputData.getErrorMessage());
    }

    @Test
    public void testConstructorWithEmptyFlights() {
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(Collections.emptyList());
        assertNotNull("Filtered flights should not be null even if the list is empty", outputData.getFilteredFlights());
        assertTrue("Filtered flights list should be empty", outputData.getFilteredFlights().isEmpty());
        assertNull("Error message should be null when no error is passed", outputData.getErrorMessage());
    }

    @Test
    public void testConstructorWithSpecialCharacterErrorMessage() {
        String specialMessage = "Error: Flight not found! 🚀✈️";
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(specialMessage);
        assertEquals("The error message should match the input with special characters", specialMessage,
                outputData.getErrorMessage());
        assertNull("Filtered flights should be null in failure case", outputData.getFilteredFlights());
    }

    @Test
    public void testConstructorWithNullFlights() {
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData((List<Flight>) null);
        assertNull("Filtered flights should be null when input list is null", outputData.getFilteredFlights());
        assertNull("Error message should be null when no error is passed", outputData.getErrorMessage());
    }

    @Test
    public void testConstructorWithNullErrorMessage() {
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData((String) null);
        assertNull("Filtered flights should be null when no flights are provided", outputData.getFilteredFlights());
        assertNull("Error message should be null when no error message is provided", outputData.getErrorMessage());
    }
}
