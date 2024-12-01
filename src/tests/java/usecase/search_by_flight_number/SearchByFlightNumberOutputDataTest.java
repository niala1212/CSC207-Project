package usecase.search_by_flight_number;

import org.junit.Assert;
import org.junit.Test;
import entities.Flight;

public class SearchByFlightNumberOutputDataTest {

    @Test
    public void testSuccessConstructor() {
        // Create a flight object with flight number and date
        Flight flight = new Flight("123", "2024-11-26");

        // Set the scheduled departure and arrival times
        flight.setScheduledDepartureTime("2024-11-26T10:00");
        flight.setScheduledArrivalTime("2024-11-26T14:00");

        // Create output data using the flight object
        SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(flight);

        // Assertions to check if the times are set correctly
        Assert.assertEquals("123", outputData.getFlightNumber());
        Assert.assertEquals("2024-11-26T10:00UTC", outputData.getDepartureTime());
        Assert.assertEquals("2024-11-26T14:00UTC", outputData.getArrivalTime());
        Assert.assertNull(outputData.getErrorMessage());  // Ensure there's no error message
    }

    @Test
    public void testFailureConstructor() {
        // Create an output data with an error message (when no flight found)
        SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData("No flight found");

        // Assertions to check that all values are null when no flight data is found
        Assert.assertNull(outputData.getFlightNumber());  // Should return null
        Assert.assertNull(outputData.getDepartureTime());  // Should return null
        Assert.assertNull(outputData.getArrivalTime());  // Should return null
        Assert.assertNull(outputData.getStatus());  // Should return null
        Assert.assertEquals("No flight found", outputData.getErrorMessage());
    }
}
