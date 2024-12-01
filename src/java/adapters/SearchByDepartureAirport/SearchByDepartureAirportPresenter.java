package adapters.SearchByDepartureAirport;

import java.util.ArrayList;
import java.util.List;

import entities.Flight;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputData;

/**
 * Presenter for the Departures by Airport use case.
 */
public class SearchByDepartureAirportPresenter implements SearchByDepartureAirportOutputBoundary {

    private SearchByDepartureAirportViewModel viewModel;
    private final String errorPropertyName = "noFlightsError";

    public SearchByDepartureAirportPresenter(SearchByDepartureAirportViewModel searchByDepartureAirportViewModel) {
        this.viewModel = searchByDepartureAirportViewModel;
    }

    /**
     * Clears the previous results.
     * @param outputData the output data containing flight information.
     */
    public void clearView(SearchByDepartureAirportOutputData outputData) {
        SearchByDepartureAirportState state = viewModel.getState();
        state.clearPreviousResults();
        state.setMessage(outputData.getDepartureErrorMessage());
        viewModel.firePropertyChanged(errorPropertyName);
    }

    @Override
    public void prepareSuccessView(SearchByDepartureAirportOutputData outputData) {
        clearView(outputData);
        SearchByDepartureAirportState state = viewModel.getState();
        List<Flight> departureFlights = outputData.getDepartureFlights();
        if (departureFlights.isEmpty()) {
            state.setMessage("No flights available for this airport.");
            viewModel.firePropertyChanged(errorPropertyName);
        }
        else {
            String airportName = departureFlights.get(0).getDepartureAirport();
            List<String> flightNumbers = extractFlightNumbers(departureFlights);
            state.setAirportName(airportName);
            state.setFlightNumbers(flightNumbers);
            state.setFlights(departureFlights);
            viewModel.firePropertyChanged("airportFlights");
        }
    }

    @Override
    public void prepareFailView(SearchByDepartureAirportOutputData outputData) {
        // Clear the previous state of the UI before showing the error message
        clearView(outputData);

        SearchByDepartureAirportState state = viewModel.getState();

        // Set the error message in the state
        state.setMessage(outputData.getDepartureErrorMessage());
        viewModel.firePropertyChanged(errorPropertyName);
    }

    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
