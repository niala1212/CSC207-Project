package adapters.SearchByAirlineID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import use_case.SearchByAirlineID.SearchByAirlineIDInputBoundary;  // Ensure this is the correct import
import use_case.SearchByAirlineID.SearchByAirlineIDInputData;    // Ensure this is the correct import
import org.mockito.ArgumentMatchers; // Add this import for argument matchers

class SearchByAirlineIDControllerTest {

    private SearchByAirlineIDController controller;
    private SearchByAirlineIDInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        // Mock the interactor
        mockInteractor = mock(SearchByAirlineIDInputBoundary.class);
        controller = new SearchByAirlineIDController(mockInteractor);
    }

    @Test
    void testExecute_ValidIataCode() {
        String validIataCode = "AA";

        // Call the method under test
        controller.execute(validIataCode);

        // Verify that the execute method of the interactor is called with the correct input
        verify(mockInteractor, times(1)).execute(ArgumentMatchers.any(SearchByAirlineIDInputData.class));
    }

    @Test
    void testExecute_WhitespaceIataCode() {
        String whitespaceIataCode = "  ";

        // Call the method under test and assert that it throws an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            controller.execute(whitespaceIataCode);
        });

        // Verify the exception message
        assertEquals("Airline IATA code cannot be empty.", thrown.getMessage());
    }

    @Test
    void testExecute_NullIataCode() {
        String nullIataCode = null;

        // Call the method under test and assert that it throws an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            controller.execute(nullIataCode);
        });

        // Verify the exception message
        assertEquals("Airline IATA code cannot be empty.", thrown.getMessage());
    }

    @Test
    void testExecute_EmptyIataCode() {
        String emptyIataCode = "";

        // Call the method under test and assert that it throws an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            controller.execute(emptyIataCode);
        });

        // Verify the exception message
        assertEquals("Airline IATA code cannot be empty.", thrown.getMessage());
    }
}