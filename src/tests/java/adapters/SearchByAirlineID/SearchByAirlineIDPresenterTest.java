package adapters.SearchByAirlineID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import use_case.SearchByAirlineID.SearchByAirlineIDOutputData;
import entities.Flight;

class SearchByAirlineIDPresenterTest {

    private SearchByAirlineIDViewModel mockViewModel;
    private SearchByAirlineIDPresenter presenter;

    @BeforeEach
    void setUp() {
        // Create the mock ViewModel
        mockViewModel = mock(SearchByAirlineIDViewModel.class);
        presenter = new SearchByAirlineIDPresenter(mockViewModel);
    }

    @Test
    void testPrepareSuccessView_FlightsFound() {
        // Create a mock list of flights
        Flight flight1 = mock(Flight.class);
        Flight flight2 = mock(Flight.class);
        when(flight1.getFlightNumber()).thenReturn("AA100");
        when(flight2.getFlightNumber()).thenReturn("AA200");
        when(flight1.getDepartureAirport()).thenReturn("JFK");
        when(flight2.getDepartureAirport()).thenReturn("JFK");

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create the output data with the flights
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(flights);

        // Call the method
        presenter.prepareSuccessView(outputData);

        // Verify the ViewModel was updated correctly
        verify(mockViewModel, times(1)).updateFlights("JFK", Arrays.asList("AA100", "AA200"), flights);
    }

    @Test
    void testPrepareSuccessView_NoFlightsFound() {
        // Create an empty list of flights
        List<Flight> flights = Arrays.asList();

        // Create the output data with no flights
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData(flights);

        // Call the method
        presenter.prepareSuccessView(outputData);

        // Verify the ViewModel was updated with the "no flights found" message
        verify(mockViewModel, times(1)).updateNoFlightsFound("No flights found for the specified airline.");
    }

    @Test
    void testPrepareFailView() {
        // Create the output data with an error message
        SearchByAirlineIDOutputData outputData = new SearchByAirlineIDOutputData("Error retrieving data");

        // Call the method
        presenter.prepareFailView(outputData);

        // Verify the ViewModel was updated with the error message
        verify(mockViewModel, times(1)).updateNoFlightsFound("An error occurred: Error retrieving data");
    }
}
