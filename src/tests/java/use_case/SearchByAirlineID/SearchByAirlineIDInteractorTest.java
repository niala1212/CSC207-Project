package use_case.SearchByAirlineID;

import entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SearchByAirlineIDInteractorTest {

    @Mock
    private SearchByAirlineIDDataAccessInterface mockDataAccessObject;

    @Mock
    private SearchByAirlineIDOutputBoundary mockPresenter;

    private SearchByAirlineIDInteractor searchByAirlineIDInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchByAirlineIDInteractor = new SearchByAirlineIDInteractor(mockDataAccessObject, mockPresenter);
    }

    @Test
    void testExecute_SuccessfulFlightSearch() {
        String airlineIataCode = "AA";
        List<Flight> flights = Arrays.asList(new Flight("AA123", "2024-11-25"), new Flight("AA124", "2024-11-25"));
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode);

        when(mockDataAccessObject.getFlightsByAirlineId(airlineIataCode)).thenReturn(flights);

        searchByAirlineIDInteractor.execute(inputData);

        // Verify that the success view method is called
        verify(mockPresenter).prepareSuccessView(any(SearchByAirlineIDOutputData.class));
    }

    @Test
    void testExecute_NoFlightsFound() {
        String airlineIataCode = "AA";
        List<Flight> flights = Arrays.asList();
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode);

        when(mockDataAccessObject.getFlightsByAirlineId(airlineIataCode)).thenReturn(flights);

        searchByAirlineIDInteractor.execute(inputData);

        // Verify that the success view method is called with the appropriate message
        verify(mockPresenter).prepareSuccessView(argThat(outputData -> outputData.getErrorMessage().equals("No flights found for the specified airline.")));
    }

    @Test
    void testExecute_DataAccessError() {
        String airlineIataCode = "AA";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode);

        when(mockDataAccessObject.getFlightsByAirlineId(airlineIataCode)).thenReturn(null);

        searchByAirlineIDInteractor.execute(inputData);

        // Verify that the fail view method is called with the error message
        verify(mockPresenter).prepareFailView(argThat(outputData -> outputData.getErrorMessage().equals("Error retrieving flight data. Please try again later.")));
    }

    @Test
    void testExecute_UnexpectedError() {
        String airlineIataCode = "AA";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(airlineIataCode);

        when(mockDataAccessObject.getFlightsByAirlineId(airlineIataCode)).thenThrow(new RuntimeException("Unexpected error"));

        searchByAirlineIDInteractor.execute(inputData);

        // Verify that the fail view method is called with the appropriate message
        verify(mockPresenter).prepareFailView(argThat(outputData -> outputData.getErrorMessage().equals("An unexpected error occurred: Unexpected error")));
    }
}
