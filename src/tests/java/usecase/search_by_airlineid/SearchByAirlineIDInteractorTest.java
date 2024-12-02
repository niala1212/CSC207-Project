package usecase.search_by_airlineid;

import static org.mockito.Mockito.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;
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
        searchByAirlineIDInteractor = new SearchByAirlineIDInteractor(mockFlightDataAccessObject,
                mockSearchByAirlineIDPresenter);
    }

    @Test
    public void testExecute_FlightsFound() throws IOException {
        String airlineId = "AA";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27"));
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == flights && outputData.getErrorMessage() == null));
    }

    @Test
    public void testExecute_NoFlightsFound() throws IOException {
        String airlineId = "AA";
        List<Flight> flights = List.of();
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(flights);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFilteredFlights() == null && outputData.getErrorMessage()
                        .equals("No flights found for the specified airline.")));
    }

    @Test
    public void testExecute_FlightDataAccessReturnsNull() throws IOException {
        String airlineId = "AA";
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenReturn(null);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage()
                        .equals("Error retrieving flight data for the specified airline." +
                                " Please try a different IATA.")));
    }

    @Test
    public void testExecute_ExceptionThrown() throws IOException {
        String airlineId = "AA";
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);
        when(mockFlightDataAccessObject.getFlightsByAirlineId(airlineId)).thenThrow(new RuntimeException("API Error"));

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("An unexpected error occurred:\nAPI ERROR")));
    }

    @Test
    public void testExecute_EmptyAirlineId() {
        String airlineId = "";
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Airline IATA code cannot be empty.")));
    }

    @Test
    public void testExecute_InvalidAirlineIdPattern() {
        String airlineId = "ABC"; // Invalid: more than 2 characters
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Invalid Airline IATA code.")));
    }

    @Test
    public void testExecute_NullAirlineId() {
        String airlineId = null;
        SearchByAirlineIDInputData inputData = mock(SearchByAirlineIDInputData.class);
        when(inputData.getAirlineIataCode()).thenReturn(airlineId);

        searchByAirlineIDInteractor.execute(inputData);

        verify(mockSearchByAirlineIDPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Airline IATA code cannot be empty.")));
    }
}
