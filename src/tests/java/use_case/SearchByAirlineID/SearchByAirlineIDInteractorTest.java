package use_case.SearchByAirlineID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

public class SearchByAirlineIDInteractorTest {

    @Mock
    private SearchByAirlineIDDataAccessInterface mockFlightDataAccessObject;

    @Mock
    private SearchByAirlineIDOutputBoundary mockSearchByAirlineIDPresenter;

    private SearchByAirlineIDInteractor searchByAirlineIDInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        searchByAirlineIDInteractor = new SearchByAirlineIDInteractor(mockFlightDataAccessObject, mockSearchByAirlineIDPresenter);
    }

    @Test
    public void testExecute_FlightsFound() {
        // Arrange
        String airlineId = "AA";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"), new Flight("AB321", "2024-12-27")); // Assuming Flight is a valid entity
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act
        searchByAirlineIDInteractor.execute(inputData);

        // Assert
        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == flights && outputData.getErrorMessage() == null));
    }

    @Test
    public void testExecute_NoFlightsFound() {
        // Arrange
        String airlineId = "AA";
        List<Flight> flights = Arrays.asList();
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act
        searchByAirlineIDInteractor.execute(inputData);

        // Assert
        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == null && outputData.getErrorMessage().equals("No flights found for the specified airline.")));
    }

    @Test
    public void testExecute_FlightDataAccessReturnsNull() {
        // Arrange
        String airlineId = "AA";
        List<Flight> flights = null;
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act
        searchByAirlineIDInteractor.execute(inputData);

        // Assert
        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Error retrieving flight data for the specified airline. Please try a different iata.")));
    }

    @Test
    public void testExecute_ExceptionThrown() {
        // Arrange
        String airlineId = "AA";
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenThrow(new RuntimeException("API Error"));

        // Act
        searchByAirlineIDInteractor.execute(inputData);

        // Assert
        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("An unexpected error occurred:\nAPI ERROR")));
    }
}
