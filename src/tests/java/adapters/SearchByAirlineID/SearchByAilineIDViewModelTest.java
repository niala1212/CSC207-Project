package adapters.SearchByAirlineID;

import entities.Flight;
import entities.FlightFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchByAirlineIDViewModelTest {

    private SearchByAirlineIDViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SearchByAirlineIDViewModel();
    }

    @Test
    void testUpdateFlights() {
        // Given some example flight data
        String airportName = "JFK Airport";
        List<String> flightNumbers = Arrays.asList("AA100", "AA101");

        // Creating Flight objects using the FlightFactory
        List<Flight> flights = Arrays.asList(
                FlightFactory.create(
                        Arrays.asList("AA100", "2024/11/25", "American Airlines", "JFK", "LAX", "On time", "2024/11/25 08:00", "2024/11/25 08:30", "2024/11/25 12:00", "2024/11/25 12:30"),
                        new double[]{40.6413, -73.7781}
                ),
                FlightFactory.create(
                        Arrays.asList("AA101", "2024/11/25", "American Airlines", "LAX", "JFK", "Delayed", "2024/11/25 09:00", "2024/11/25 09:30", "2024/11/25 13:00", "2024/11/25 13:30"),
                        new double[]{34.0522, -118.2437}
                )
        );

        // When calling updateFlights
        viewModel.updateFlights(airportName, flightNumbers, flights);

        // Then check the updated state
        assertEquals(airportName, viewModel.getAirportName());
        assertEquals(flightNumbers, viewModel.getFlightNumbers());
        assertEquals(flights, viewModel.getFlights());
    }

    @Test
    void testUpdateNoFlightsFound() {
        // Given a message for no flights found
        String message = "No flights found for the specified airline.";

        // When calling updateNoFlightsFound
        viewModel.updateNoFlightsFound(message);

        // Then check the updated state
        assertEquals(message, viewModel.getAirportName());  // Now this should return the message
        assertTrue(viewModel.getFlightNumbers().isEmpty());
        assertTrue(viewModel.getFlights().isEmpty());
    }
}
