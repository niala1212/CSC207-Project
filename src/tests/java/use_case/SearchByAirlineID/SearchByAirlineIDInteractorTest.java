package use_case.SearchByAirlineID;

import static org.mockito.Mockito.*;

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

    // Set up method to initialize the test environment and mocks
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock annotations
        searchByAirlineIDInteractor = new SearchByAirlineIDInteractor(mockFlightDataAccessObject,
                mockSearchByAirlineIDPresenter);
    }

    // Test case when flights are found for the given airline ID
    @Test
    public void testExecute_FlightsFound() {
        // Arrange: Prepare mock data
        String airlineId = "AA";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27"));
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        // Mock input data returning the airline ID
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        // Mock data access returning the flights
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act: Execute the use case method
        searchByAirlineIDInteractor.execute(inputData);

        // Assert: Verify the presenter was called with the correct output data
        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == flights && outputData.getErrorMessage() == null));
    }

    // Test case when no flights are found for the given airline ID
    @Test
    public void testExecute_NoFlightsFound() {
        // Arrange: Prepare mock data with no flights
        String airlineId = "AA";
        List<Flight> flights = List.of();  // No flights
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act: Execute the use case method
        searchByAirlineIDInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message indicating no flights found
        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == null && outputData.getErrorMessage()
                        .equals("No flights found for the specified airline.")));
    }

    // Test case when the flight data access returns null
    @Test
    public void testExecute_FlightDataAccessReturnsNull() {
        // Arrange: Prepare mock data with null flights
        String airlineId = "AA";
        List<Flight> flights = null;  // Simulate no data from the data access layer
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        // Act: Execute the use case method
        searchByAirlineIDInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message indicating a problem retrieving data
        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().
                        equals("Error retrieving flight data for the specified airline. " +
                                "Please try a different iata.")));
    }

    // Test case when an exception is thrown during the flight data access
    @Test
    public void testExecute_ExceptionThrown() {
        // Arrange: Prepare mock data with an exception being thrown by the data access object
        String airlineId = "AA";
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenThrow(new RuntimeException("API Error"));

        // Act: Execute the use case method
        searchByAirlineIDInteractor.execute(inputData);

        // Assert: Verify the presenter was called with a failure message indicating an unexpected error
        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("An unexpected error occurred:\nAPI ERROR")));
    }
}
