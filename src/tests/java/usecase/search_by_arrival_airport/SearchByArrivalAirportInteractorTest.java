package usecase.search_by_arrival_airport;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SearchByArrivalAirportInteractorTest {

    @Mock
    private SearchByArrivalAirportDataAccessInterface mockFlightDataAccessObject;

    @Mock
    private SearchByArrivalAirportOutputBoundary mockSearchByArrivalAirportPresenter;

    private SearchByArrivalAirportInteractor searchByArrivalAirportInteractor;

    // Set up method to initialize the test environment and mocks
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock annotations
        searchByArrivalAirportInteractor = new SearchByArrivalAirportInteractor(mockFlightDataAccessObject,
                mockSearchByArrivalAirportPresenter);
    }

    // Test case when flights are found for the given arrival airport code
    @Test
    public void testExecute_FlightsFound() throws IOException {
        // Arrange: Prepare mock data
        String airportCode = "XYZ";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27"));
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        // Mock input data returning the airport code
        when(inputData.getAirportCode()).thenReturn(airportCode);
        // Mock data access returning the flights
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(flights);

        // Act: Execute the use case method
        searchByArrivalAirportInteractor.execute(inputData);

        // Assert: Verify the presenter was called with the correct output data
        verify(mockSearchByArrivalAirportPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredArrivalFlights() == flights && outputData.getArrivalErrorMessage() == null));
    }

    // Test case when no flights are found for the given arrival airport code
    @Test
    public void testExecute_NoFlightsFound() throws IOException {
        // Arrange: Prepare mock data with no flights
        String airportCode = "XYZ";
        List<Flight> flights = List.of();  // No flights
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(flights);

        // Act: Execute the use case method
        searchByArrivalAirportInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message indicating no flights found
        verify(mockSearchByArrivalAirportPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredArrivalFlights() == null && outputData.getArrivalErrorMessage()
                        .equals("No arrival flights found for the specified airport.")));
    }

    // Test case when the flight data access returns null
    @Test
    public void testExecute_FlightDataAccessReturnsNull() throws IOException {
        // Arrange: Prepare mock data with null flights
        String airportCode = "XYZ";
        List<Flight> flights = null;  // Simulate no data from the data access layer
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(flights);

        // Act: Execute the use case method
        searchByArrivalAirportInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message indicating a problem retrieving data
        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("Error retrieving flight data. Please try again later.")));
    }

    // Test case when an exception is thrown during the flight data access
    @Test
    public void testExecute_ExceptionThrown() throws IOException {
        // Arrange: Prepare mock data with an exception being thrown by the data access object
        String airportCode = "XYZ";
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenThrow(new RuntimeException("API Error"));

        // Act: Execute the use case method
        searchByArrivalAirportInteractor.execute(inputData);

        // Assert: Verify the presenter was called with a failure message indicating an unexpected error
        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("An unexpected error occurred")));
    }
}
