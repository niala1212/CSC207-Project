package adapters.SearchByAirlineID;

import use_case.SearchByAirlineID.SearchByAirlineIDOutputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDOutputData;
import entities.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * The Presenter for the Search By Airline ID Use Case.
 */
public class SearchByAirlineIDPresenter implements SearchByAirlineIDOutputBoundary {

    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel;

    public SearchByAirlineIDPresenter(SearchByAirlineIDViewModel searchByAirlineIDViewModel) {
        this.searchByAirlineIDViewModel = searchByAirlineIDViewModel;
    }

    @Override
    public void prepareSuccessView(SearchByAirlineIDOutputData outputData) {
        SearchByAirlineIDState state = searchByAirlineIDViewModel.getState();
        List<Flight> flights = outputData.getFilteredFlights();
        if (outputData.isUseCaseFailed()) {
            // Case: No flights found - update the state
            state.setErrorMessage(outputData.getErrorMessage());

            // Update the ViewModel with the changed property
            searchByAirlineIDViewModel.firePropertyChanged("noFlightsError");
        } else {
            // Case: Flights found
            String airlineName = flights.get(0).getAirline(); // Example: Use the departure airport as airport name
            List<String> flightNumbers = extractFlightNumbers(flights);

            //Update the state
            state.setAirlineName(airlineName);
            state.setFlightNumbers(flightNumbers);
            state.setFlights(flights);

            // Update the ViewModel with the changed property
            searchByAirlineIDViewModel.firePropertyChanged("airlineFlights");
        }
    }

    @Override
    public void prepareFailView(SearchByAirlineIDOutputData outputData) {
        SearchByAirlineIDState state = searchByAirlineIDViewModel.getState();

        // Case: Error occurred, update state with an error message
        state.setErrorMessage(outputData.getErrorMessage());

        // Update the ViewModel with the changed property
        searchByAirlineIDViewModel.firePropertyChanged("noFlightsError");
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

