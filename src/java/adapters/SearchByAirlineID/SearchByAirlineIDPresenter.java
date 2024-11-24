package adapters.SearchByAirlineID;

import use_case.SearchByAirlineID.SearchByAirlineIDOutputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDOutputData;
import entities.Flight;

import java.util.ArrayList;
import java.util.List;

public class SearchByAirlineIDPresenter implements SearchByAirlineIDOutputBoundary {

    private final SearchByAirlineIDViewModel viewModel;

    public SearchByAirlineIDPresenter(SearchByAirlineIDViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchByAirlineIDOutputData outputData) {
        List<Flight> flights = outputData.getFilteredFlights();
        if (flights.isEmpty()) {
            // Case: No flights found
            viewModel.updateNoFlightsFound("No flights found for the specified airline.");
        } else {
            // Case: Flights found
            String airportName = flights.get(0).getDepartureAirport(); // Example: Use the departure airport as airport name
            List<String> flightNumbers = extractFlightNumbers(flights);

            // Update the ViewModel with the full list of flights
            viewModel.updateFlights(airportName, flightNumbers, flights);
        }
    }

    @Override
    public void prepareFailView(SearchByAirlineIDOutputData outputData) {
        // Case: Error occurred, update ViewModel with an error message
        viewModel.updateNoFlightsFound("An error occurred: " + outputData.getErrorMessage());
    }

    // Helper method to extract flight numbers from the list of flights
    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}

