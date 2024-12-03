package usecase.search_airport_landed;

import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchAirportLandedOutputDataTest {

    private List<Flight> flights;
    private SearchAirportLandedOutputData successOutputData;
    private SearchAirportLandedOutputData failureOutputData;

    @Before
    public void setUp() {
        // Mock data for flights
        flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27")); // Assuming Flight is a valid entity

        // Create SearchAirportLandedOutputData instances
        successOutputData = new SearchAirportLandedOutputData(flights);
        failureOutputData = new SearchAirportLandedOutputData("ErRor!!!!");
    }

    @Test
    public void testSuccessConstructor() {
        assertNull("Error message should be null in success case", successOutputData.getErrorMessage());
        int i = 0;
        for (FlightOutputData flightOutputData : successOutputData.getFlightOutputDataList()) {
            assertEquals("Each flight output data should match", flightOutputData.getFlight(), flights.get(i));
            i++;
        }
    }

    @Test
    public void testFailureConstructor() {
        assertNull("Flight output data list should be null", failureOutputData.getFlightOutputDataList());
        assertEquals("The error message should be", "ErRor!!!!", failureOutputData.getErrorMessage());
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
    public void testConstructorWithEmptyFlights() {
        SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(Collections.emptyList());
        assertNull("Flight output data list should be null", failureOutputData.getFlightOutputDataList());
        assertNull("Error message should still be null", outputData.getErrorMessage());
    }

    @Test
    public void testConstructorWithSpecialCharacterErrorMessage() {
        String specialMessage = "Error! 🚀✈️";
        SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData(specialMessage);
        assertEquals("The error message should match the input with special characters", specialMessage,
                outputData.getErrorMessage());
        assertNull("Flight output data list should be null", failureOutputData.getFlightOutputDataList());
    }

    @Test
    public void testConstructorWithNullFlights() {
        SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData((List<Flight>) null);
        assertNull("Flight output data list should be null when input list is null", outputData.getFlightOutputDataList());
        assertNull("Error message should be null when no error is passed", outputData.getErrorMessage());
    }

    @Test
    public void testConstructorWithNullErrorMessage() {
        SearchAirportLandedOutputData outputData = new SearchAirportLandedOutputData((String) null);
        assertNull("Error message should be null when no error message is provided", outputData.getErrorMessage());
        assertNull("Flight output data list should be null", outputData.getFlightOutputDataList());
    }
}
