package adapters.SearchByArrivalAirport;

import adapters.AbstractViewModel;
import entities.Flight;
import java.util.List;

public class SearchByArrivalAirportViewModel extends AbstractViewModel<SearchByArrivalAirportState> {

    public SearchByArrivalAirportViewModel() {
        super(ViewState.SEARCHBYAIRPORTID); // Initial state when searching by airport
    }

    // Method to update flights and airport information when found
    public void updateFlights(String airportCode, List<String> flightNumbers, List<Flight> flights) {
        // Store the data in the state for later use
        setState(new SearchByArrivalAirportState(airportCode, flightNumbers, flights));
    }

    // Method for handling no flights found or errors
    public void updateNoFlightsFound(String message) {
        setState(new SearchByArrivalAirportState(message));
    }

    // Getters for UI access
    public String getAirportCode() {
        // Ensure the state is properly updated with the message
        return getState() != null ? getState().getAirportName() : "No state available";
    }

    public List<String> getFlightNumbers() {
        return getState() != null ? getState().getFlightNumbers() : List.of();
    }

    public List<Flight> getFlights() {
        return getState() != null ? getState().getFlights() : List.of();
    }
}
