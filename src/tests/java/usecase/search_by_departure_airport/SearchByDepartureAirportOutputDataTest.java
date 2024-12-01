package usecase.search_by_departure_airport;

import entities.Flight;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchByDepartureAirportOutputDataTest {

    @Test
    void testConstructor_Success() {
        // Arrange
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AA123", "2023-12-15"));
        flights.add(new Flight("DL456", "2023-12-16"));

        // Act
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(flights);

        // Assert
        assertNotNull(outputData.getDepartureFlights());
        assertEquals(2, outputData.getDepartureFlights().size());
        assertNull(outputData.getDepartureErrorMessage());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructor_Failure() {
        // Arrange
        String errorMessage = "No flights found for the given airport.";

        // Act
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(errorMessage);

        // Assert
        assertNull(outputData.getDepartureFlights());
        assertEquals(errorMessage, outputData.getDepartureErrorMessage());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testGetDepartureFlights_WhenNull() {
        // Arrange
        String errorMessage = "Error occurred.";
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(errorMessage);

        // Act
        List<Flight> flights = outputData.getDepartureFlights();

        // Assert
        assertNull(flights);
    }

    @Test
    void testGetDepartureErrorMessage_WhenNull() {
        // Arrange
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AA123", "2023-12-15"));
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(flights);

        // Act
        String errorMessage = outputData.getDepartureErrorMessage();

        // Assert
        assertNull(errorMessage);
    }

    @Test
    void testIsUseCaseFailed_Success() {
        // Arrange
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AA123", "2023-12-15"));
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(flights);

        // Act
        boolean result = outputData.isUseCaseFailed();

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsUseCaseFailed_Failure() {
        // Arrange
        String errorMessage = "No flights found.";
        SearchByDepartureAirportOutputData outputData = new SearchByDepartureAirportOutputData(errorMessage);

        // Act
        boolean result = outputData.isUseCaseFailed();

        // Assert
        assertTrue(result);
    }
}
