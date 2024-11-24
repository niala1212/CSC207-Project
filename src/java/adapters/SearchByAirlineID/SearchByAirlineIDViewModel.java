package adapters.SearchByAirlineID;

import adapters.AbstractViewModel;
import entities.Flight;
import java.util.List;

public class SearchByAirlineIDViewModel extends AbstractViewModel<SearchByAirlineIDState> {

    public SearchByAirlineIDViewModel() {
        super(State.SEARCHBYAIRLINEID); // Initial state when searching by airline ID
    }

    // Method to update the flights and airport name when found
    public void updateFlights(String airportName, List<String> flightNumbers, List<Flight> flights) {
        // Store the data in the state for later use
        setState(new SearchByAirlineIDState(airportName, flightNumbers, flights));
    }

    // Method for no flights found or error
    public void updateNoFlightsFound(String message) {
        setState(new SearchByAirlineIDState(message));
    }

    // Getters for UI access
    public String getAirportName() {
        return getState().getAirportName();
    }

    public List<String> getFlightNumbers() {
        return getState().getFlightNumbers();
    }

    public List<Flight> getFlights() {
        return getState().getFlights();  // Allows access to full flight data
    }
}

