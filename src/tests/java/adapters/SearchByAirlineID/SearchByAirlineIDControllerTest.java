package adapters.SearchByAirlineID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.SearchByAirlineID.SearchByAirlineIDInputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDInputData;

import static org.mockito.Mockito.*;

public class SearchByAirlineIDControllerTest {

    private SearchByAirlineIDController controller;
    private SearchByAirlineIDInputBoundary mockInputBoundary;

    @BeforeEach
    public void setUp() {
        // Mock the input boundary
        mockInputBoundary = Mockito.mock(SearchByAirlineIDInputBoundary.class);
        controller = new SearchByAirlineIDController(mockInputBoundary);
    }

    @Test
    void testSearchFlightsByAirline_ValidInput() {
        // Given a valid IATA code
        String validIataCode = "AA";

        // Call the method
        controller.execute(validIataCode);

        // Verify that the execute method was called on the input boundary with the correct data
        verify(mockInputBoundary, times(1)).execute(new SearchByAirlineIDInputData(validIataCode));
    }

    @Test
    void testSearchFlightsByAirline_InvalidInput_ThrowsException() {
        // Given an invalid IATA code (empty string)
        String invalidIataCode = "";

        // Expect an IllegalArgumentException to be thrown
        try {
            controller.execute(invalidIataCode);
        } catch (IllegalArgumentException e) {
            // Verify the exception message
            assert e.getMessage().equals("Airline IATA code cannot be empty.");
        }

        // Verify that the execute method was not called on the input boundary
        verify(mockInputBoundary, times(0)).execute(any());
    }

    @Test
    public void testSearchFlightsByAirline_NullInput_ThrowsException() {
        // Given a null IATA code
        String nullIataCode = null;

        // Expect an IllegalArgumentException to be thrown
        try {
            controller.execute(nullIataCode);
        } catch (IllegalArgumentException e) {
            // Verify the exception message
            assert e.getMessage().equals("Airline IATA code cannot be empty.");
        }

        // Verify that the execute method was not called on the input boundary
        verify(mockInputBoundary, times(0)).execute(any());
    }
}

