package usecase.search_airport_landed;

import static org.mockito.Mockito.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

public class SearchAirportLandedInteractorTest {

    @Mock
    private SearchAirportLandedDataAccessInterface mockFlightDataAccessObject;

    @Mock
    private SearchAirportLandedOutputBoundary mockSearchAirportLandedPresenter;

    private SearchAirportLandedInteractor searchAirportLandedInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        searchAirportLandedInteractor = new SearchAirportLandedInteractor(mockFlightDataAccessObject,
                mockSearchAirportLandedPresenter);
    }

    @Test
    public void testExecute_FlightsFound() {
        String airportCode = "YVR";
        List<Flight> flights = Arrays.asList(new Flight("AB123", "2024-11-26"),
                new Flight("AB321", "2024-12-27"));
        SearchAirportLandedInputData inputData = mock(SearchAirportLandedInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getLandedFlightsByAirport(airportCode)).thenReturn(flights);

        searchAirportLandedInteractor.execute(inputData);

        verify(mockSearchAirportLandedPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFlightList() == flights && outputData.getErrorMessage() == null));
    }

    @Test
    public void testExecute_NoFlightsFound() {
        String airportCode = "YVR";
        List<Flight> flights = List.of();
        SearchAirportLandedInputData inputData = mock(SearchAirportLandedInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getLandedFlightsByAirport(airportCode)).thenReturn(flights);

        searchAirportLandedInteractor.execute(inputData);

        verify(mockSearchAirportLandedPresenter).prepareFailView(argThat(outputData ->
                outputData.getFlightOutputDataList() == null && outputData.getErrorMessage()
                        .equals("Sorry, no landed flights have been found at the airport \"" + airportCode + "\"")));
    }

    @Test
    public void testExecute_FlightDataAccessReturnsNull() {
        String airportCode = "asdss";
        SearchAirportLandedInputData inputData = mock(SearchAirportLandedInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportCode);
        when(mockFlightDataAccessObject.getLandedFlightsByAirport(airportCode)).thenReturn(null);

        searchAirportLandedInteractor.execute(inputData);

        verify(mockSearchAirportLandedPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage()
                        .equals("Error retrieving the requested landed flights at \"" + airportCode
                                + "\". Please enter a valid IATA airport code.")));
    }

    @Test
    public void testExecute_ExceptionThrown() {
        String airportID = "YVR";
        SearchAirportLandedInputData inputData = mock(SearchAirportLandedInputData.class);
        when(inputData.getAirportCode()).thenReturn(airportID);
        when(mockFlightDataAccessObject.getLandedFlightsByAirport(airportID)).thenThrow(new RuntimeException("API ERROR"));

        searchAirportLandedInteractor.execute(inputData);

        verify(mockSearchAirportLandedPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("An unexpected error occurred: API ERROR")));
    }
}
