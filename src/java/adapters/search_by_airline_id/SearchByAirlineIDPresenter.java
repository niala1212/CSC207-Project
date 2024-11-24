package adapters.search_by_airline_id;

import entities.Flight;
import gui.SearchByAirlineIDView;
import use_case.search_by_airline_id.SearchByAirlineIDOutputBoundary;
import use_case.search_by_airline_id.SearchByAirlineIDOutputData;

import java.util.ArrayList;
import java.util.List;

public class SearchByAirlineIDPresenter implements SearchByAirlineIDOutputBoundary {
    private final SearchByAirlineIDView view;

    public SearchByAirlineIDPresenter(SearchByAirlineIDView view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(SearchByAirlineIDOutputData outputData) {
        if (outputData.getFilteredFlights().isEmpty()) {
            // Case: Valid input but no flights found
            view.displayMessage(outputData.getErrorMessage());
        } else {
            // Case: Flights successfully retrieved
            String airportName = outputData.getFilteredFlights().get(0).getDepartureAirport(); // Example: Getting the departure airport
            List<Flight> flights = outputData.getFilteredFlights();
            List<String> flightNumbers = new ArrayList<>();

            for (Flight flight : flights) {
                flightNumbers.add(flight.getFlightNumber());
            }

            view.displayFlights(airportName, flightNumbers);
        }
    }

    @Override
    public void prepareFailView(SearchByAirlineIDOutputData outputData) {
        // Case: Critical failure or unexpected error
        view.displayError(outputData.getErrorMessage());
    }
}
