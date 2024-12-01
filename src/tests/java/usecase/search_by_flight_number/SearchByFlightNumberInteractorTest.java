package usecase.search_by_flight_number;

import static org.mockito.Mockito.*;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalDate;

public class SearchByFlightNumberInteractorTest {

    @Mock
    private SearchByFlightNumberDataAccessInterface mockFlightDataAccessObject;

    @Mock
    private SearchByFlightNumberOutputBoundary mockSearchByFlightNumberPresenter;

    private SearchByFlightNumberInteractor searchByFlightNumberInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock annotations
        searchByFlightNumberInteractor = new SearchByFlightNumberInteractor(mockFlightDataAccessObject,
                mockSearchByFlightNumberPresenter);
    }

    // Test case when a flight is found by flight number
    @Test
    public void testExecute_FlightFound() {
        // Arrange: Prepare mock data
        String flightNumber = "AB123";
        Flight foundFlight = new Flight(flightNumber, "2024-11-26"); // Example flight object
        SearchByFlightNumberInputData inputData = mock(SearchByFlightNumberInputData.class);
        when(inputData.getFlightNumber()).thenReturn(flightNumber);
        when(mockFlightDataAccessObject.getFlightByFlightNumber(flightNumber, LocalDate.now().toString()))
                .thenReturn(foundFlight);

        // Act: Execute the use case method
        searchByFlightNumberInteractor.execute(inputData);

        // Assert: Verify the presenter was called with the correct output data
        verify(mockSearchByFlightNumberPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFlightNumber().equals(flightNumber) &&
                        outputData.getErrorMessage() == null));
    }

    // Test case when no flight is found by flight number
    @Test
    public void testExecute_NoFlightFound() {
        // Arrange: Prepare mock data with no flight found
        String flightNumber = "AB123";
        SearchByFlightNumberInputData inputData = mock(SearchByFlightNumberInputData.class);
        when(inputData.getFlightNumber()).thenReturn(flightNumber);
        when(mockFlightDataAccessObject.getFlightByFlightNumber(flightNumber, LocalDate.now().toString()))
                .thenReturn(null);

        // Act: Execute the use case method
        searchByFlightNumberInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message
        verify(mockSearchByFlightNumberPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Error retrieving the requested flight data of \"" + flightNumber
                        + "\". Please enter a valid IATA flight number")));
    }

    // Test case when an exception occurs during data access
    @Test
    public void testExecute_ExceptionThrown() {
        // Arrange: Prepare mock data where an exception will be thrown
        String flightNumber = "AB123";
        SearchByFlightNumberInputData inputData = mock(SearchByFlightNumberInputData.class);
        when(inputData.getFlightNumber()).thenReturn(flightNumber);
        when(mockFlightDataAccessObject.getFlightByFlightNumber(flightNumber, LocalDate.now().toString()))
                .thenThrow(new RuntimeException("API Error"));

        // Act: Execute the use case method
        searchByFlightNumberInteractor.execute(inputData);

        // Assert: Verify the presenter was called with a failure message indicating an unexpected error
        verify(mockSearchByFlightNumberPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("An unexpected error occurred: API Error")));
    }
}
