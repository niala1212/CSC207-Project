package usecase.search_by_arrival_airport;

import static org.mockito.Mockito.*;

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

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        searchByArrivalAirportInteractor = new SearchByArrivalAirportInteractor(mockFlightDataAccessObject,
                mockSearchByArrivalAirportPresenter);
    }

    @Test
    public void testExecute_FlightsFound() throws IOException {
        // Arrange: Prepare mock data
        String airportCode = "XYZ";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("CD456", "2024-12-27"));
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(flights);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredArrivalFlights() == flights && outputData.getArrivalErrorMessage() == null));
    }

    @Test
    public void testExecute_NoFlightsFound() throws IOException {
        // Arrange: Prepare mock data with no flights
        String airportCode = "XYZ";
        List<Flight> flights = List.of();  // No flights
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(flights);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredArrivalFlights() == null && outputData.getArrivalErrorMessage()
                        .equals("No arrival flights found for the specified airport.")));
    }

    @Test
    public void testExecute_FlightDataAccessReturnsNull() throws IOException {
        // Arrange: Prepare mock data with null flights
        String airportCode = "XYZ";
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenReturn(null);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("Error retrieving flight data. Please try again later.")));
    }

    @Test
    public void testExecute_ExceptionThrown() throws IOException {
        // Arrange: Prepare mock data with an exception being thrown by the data access object
        String airportCode = "XYZ";
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getArrivalFlights(airportCode)).thenThrow(new RuntimeException("API Error"));

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("An unexpected error occurred")));
    }

    @Test
    public void testExecute_EmptyAirportCode() {
        String airportCode = "";
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("Airport code cannot be empty.")));
    }

    @Test
    public void testExecute_InvalidAirportCodePattern() {
        // Invalid: contains non-alphanumeric characters
        String airportCode = "JKF#";
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("Invalid Airline IATA code.")));
    }

    @Test
    public void testExecute_NullAirportCode() {
        String airportCode = null;
        SearchByArrivalAirportInputData inputData = mock(SearchByArrivalAirportInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);

        searchByArrivalAirportInteractor.execute(inputData);

        verify(mockSearchByArrivalAirportPresenter).prepareFailView(argThat(outputData ->
                outputData.getArrivalErrorMessage().equals("Airport code cannot be empty.")));
    }
}
