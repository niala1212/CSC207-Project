package usecase.see_world_map;

import entities.Flight;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeeWorldMapOutputDataTest {

    @Test
    public void testSuccessConstructor() {
        Flight foundFlight = new Flight("name", "2024-11-26");
        double[] coords = {0,0};
        foundFlight.setCurrentLocation(coords);
        List<Flight> foundFlights = new ArrayList<>();
        foundFlights.add(foundFlight);

        Map<Point2D. Double, Flight> realMarkers = new HashMap<>();
        realMarkers.put(new Point2D.Double(483.4,360), foundFlight);


        // Create output data using the flight object
        SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(foundFlights);

        // Assertions to check if the times are set correctly
        Assert.assertEquals(realMarkers, outputData.getFlightMarkers());
        Assert.assertNull(outputData.getErrorMessage());  // Ensure there's no error message
    }

    @Test
    public void testFailureConstructor() {
        // Create an output data with an error message (when no flight found)
        SeeWorldMapOutputData outputData = new SeeWorldMapOutputData("No flight found");

        // Assertions to check that all values are null when no flight data is found
        Assert.assertEquals("No flight found", outputData.getErrorMessage());
    }
}
