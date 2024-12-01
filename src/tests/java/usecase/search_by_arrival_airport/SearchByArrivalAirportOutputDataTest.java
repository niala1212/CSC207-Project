package usecase.search_by_arrival_airport;

import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SearchByArrivalAirportOutputDataTest {

    private List<Flight> flights;
    private SearchByArrivalAirportOutputData successOutputData;
    private SearchByArrivalAirportOutputData failureOutputData;

    @Before
    public void setUp() {
        flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27"));
        successOutputData = new SearchByArrivalAirportOutputData(flights);
        failureOutputData = new SearchByArrivalAirportOutputData("No flights found for the specified arrival airport.");
    }

    @Test
    public void testSuccessConstructor() {
        assertNotNull("Filtered flights should not be null", successOutputData.getFilteredArrivalFlights());
        assertNull("Error message should be null in success case", successOutputData.getArrivalErrorMessage());
        assertEquals("The filtered flights list should match the input", flights,
                successOutputData.getFilteredArrivalFlights());
    }

    @Test
    public void testFailureConstructor() {
        assertNull("Filtered flights should be null in failure case", failureOutputData.getFilteredArrivalFlights());
        assertNotNull("Error message should not be null in failure case", failureOutputData.getArrivalErrorMessage());
        assertEquals("The error message should match the input", "No flights found for the specified arrival airport.",
                failureOutputData.getArrivalErrorMessage());
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
    public void testGetFilteredFlightsWhenFailure() {
        assertNull("Filtered flights should be null in failure case", failureOutputData.getFilteredArrivalFlights());
    }

    @Test
    public void testGetErrorMessageWhenSuccess() {
        assertNull("Error message should be null in success case", successOutputData.getArrivalErrorMessage());
    }

    @Test
    public void testGetFilteredFlightsWhenSuccess() {
        assertNotNull("Filtered flights should not be null in success case",
                successOutputData.getFilteredArrivalFlights());
        assertEquals("The filtered flights should match the expected list",
                flights, successOutputData.getFilteredArrivalFlights());
    }

    @Test
    public void testGetErrorMessageWhenFailure() {
        assertNotNull("Error message should not be null in failure case", failureOutputData.getArrivalErrorMessage());
        assertEquals("The error message should match the input", "No flights found for the specified arrival airport.",
                failureOutputData.getArrivalErrorMessage());
    }
}
