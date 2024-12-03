package usecase.see_world_map;

import entities.Flight;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class SeeWorldMapInteractorTest {

    @Mock
    private SeeWorldMapDataAccessInterface mockMapDataAccessObject;

    @Mock
    private SeeWorldMapOutputBoundary mockSeeWorldMapPresenter;

    private SeeWorldMapInteractor seeWorldMapInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock annotations
        seeWorldMapInteractor = new SeeWorldMapInteractor(mockMapDataAccessObject,
                mockSeeWorldMapPresenter);
    }

    // Test case when a flight is found by flight number
    @Test
    public void testExecute_FlightFound() throws IOException {
        // Arrange: Prepare mock data
        String reset = "";
        Flight foundFlight = new Flight(reset, "2024-11-26");
        double[] coords = {0,0};
        foundFlight.setCurrentLocation(coords);
        List<Flight> foundFlights = new ArrayList<>();
        foundFlights.add(foundFlight);

        Map<Point2D. Double, Flight> realMarkers = new HashMap<>();
        realMarkers.put(new Point2D.Double(483.4,360), foundFlight);

        SeeWorldMapInputData inputData = mock(SeeWorldMapInputData.class);
        when(inputData.getReset()).thenReturn(reset);
        when(mockMapDataAccessObject.getRandomFlights()).thenReturn(foundFlights);

        // Act: Execute the use case method
        seeWorldMapInteractor.execute(inputData);

        // Assert: Verify the presenter was called with the correct output data
        verify(mockSeeWorldMapPresenter).prepareSuccessView(argThat(outputData ->
                outputData.getFlightMarkers().equals(realMarkers) &&
                        outputData.getErrorMessage() == null));
    }

    // Test case when no flight is found by flight number
    @Test
    public void testExecute_NoFlightFound() throws IOException {
        // Arrange: Prepare mock data with no flight found
        String reset = "";
        Flight foundFlight = new Flight(reset, "2024-11-26");
        double[] coords = {0,0};
        foundFlight.setCurrentLocation(coords);
        List<Flight> foundFlights = new ArrayList<>();
        foundFlights.add(foundFlight);

        Map<Point2D. Double, Flight> realMarkers = new HashMap<>();
        realMarkers.put(new Point2D.Double(483.4,360), foundFlight);

        SeeWorldMapInputData inputData = mock(SeeWorldMapInputData.class);
        when(inputData.getReset()).thenReturn(reset);
        when(mockMapDataAccessObject.getRandomFlights()).thenReturn(null);

        // Act: Execute the use case method
        seeWorldMapInteractor.execute(inputData);

        // Assert: Verify the presenter was called with an error message
        verify(mockSeeWorldMapPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Error retrieving the flight data, please try again")));
    }

    // Test case when an exception occurs during data access
    @Test
    public void testExecute_ExceptionThrown() throws IOException {
        // Arrange: Prepare mock data where an exception will be thrown
        String reset = "";
        Flight foundFlight = new Flight(reset, "2024-11-26");
        double[] coords = {0,0};
        foundFlight.setCurrentLocation(coords);
        List<Flight> foundFlights = new ArrayList<>();
        foundFlights.add(foundFlight);

        Map<Point2D. Double, Flight> realMarkers = new HashMap<>();
        realMarkers.put(new Point2D.Double(483.4,360), foundFlight);

        SeeWorldMapInputData inputData = mock(SeeWorldMapInputData.class);
        when(inputData.getReset()).thenReturn(reset);
        when(mockMapDataAccessObject.getRandomFlights()).thenThrow(new IOException("API Error"));

        // Act: Execute the use case method
        seeWorldMapInteractor.execute(inputData);

        // Assert: Verify the presenter was called with a failure message indicating an unexpected error
        verify(mockSeeWorldMapPresenter).prepareFailView(argThat(outputData ->
                outputData.getErrorMessage().equals("Error with program runtime. Please check relevant files and try again")));
    }
}
