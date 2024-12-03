package usecase.search_by_departure_airport;

import entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class SearchByDepartureAirportInteractorTest {

    private SearchByDepartureAirportDataAccessInterface mockDataAccess;
    private SearchByDepartureAirportOutputBoundary mockPresenter;
    private SearchByDepartureAirportInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = Mockito.mock(SearchByDepartureAirportDataAccessInterface.class);
        mockPresenter = Mockito.mock(SearchByDepartureAirportOutputBoundary.class);
        interactor = new SearchByDepartureAirportInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testExecute_InvalidAirportCode() {
        // Arrange
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData("INVALID");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("Invalid airport code: INVALID")));
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecute_NoFlightsFound() throws IOException {
        // Arrange
        String airportCode = "YYZ";
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        when(mockDataAccess.getDepartureFlightsByAirport(airportCode)).thenReturn(new ArrayList<>());

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("No flights found for the specified airport.")));
        verify(mockDataAccess).getDepartureFlightsByAirport(airportCode);
    }

    @Test
    void testExecute_FlightsFound() throws IOException {
        // Arrange
        String airportCode = "JFK";
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AA123", "2024-12-27"));
        flights.add(new Flight("DL456", "2024-11-28"));
        when(mockDataAccess.getDepartureFlightsByAirport(airportCode)).thenReturn(flights);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getDepartureFlights().equals(flights)));
        verify(mockDataAccess).getDepartureFlightsByAirport(airportCode);
    }

    @Test
    void testExecute_NullFlightsReturned() throws IOException {
        // Arrange
        String airportCode = "ORD";
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        when(mockDataAccess.getDepartureFlightsByAirport(airportCode)).thenReturn(null);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("No flights found for the specified airport.")));
        verify(mockDataAccess).getDepartureFlightsByAirport(airportCode);
    }

    @Test
    void testExecute_ExceptionThrownByDataAccess() throws IOException {
        // Arrange
        String airportCode = "ATL";
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(airportCode);
        when(mockDataAccess.getDepartureFlightsByAirport(airportCode))
                .thenThrow(new RuntimeException("Database error"));

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("An unexpected error occurred. Please try again later.")));
        verify(mockDataAccess).getDepartureFlightsByAirport(airportCode);
    }

    @Test
    void testExecute_InvalidAirportCode_ShortCode() {
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData("J");
        interactor.execute(inputData);
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("Invalid airport code: J")));
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    void testExecute_NullAirportCode() {
        SearchByDepartureAirportInputData inputData = new SearchByDepartureAirportInputData(null);
        interactor.execute(inputData);
        verify(mockPresenter).prepareFailView(argThat(outputData ->
                outputData.getDepartureErrorMessage().equals("Invalid airport code: null")));
        verifyNoInteractions(mockDataAccess);
    }
}
