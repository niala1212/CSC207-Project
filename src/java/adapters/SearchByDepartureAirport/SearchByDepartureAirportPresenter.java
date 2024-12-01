package adapters.SearchByDepartureAirport;

import entities.Flight;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for the Departures by Airport use case
 */
public class SearchByDepartureAirportPresenter implements SearchByDepartureAirportOutputBoundary {

    private SearchByDepartureAirportViewModel viewModel;

    public SearchByDepartureAirportPresenter(SearchByDepartureAirportViewModel searchByDepartureAirportViewModel) {
        this.viewModel = searchByDepartureAirportViewModel;
    }

    public void clearView(SearchByDepartureAirportOutputData outputData) {
        SearchByDepartureAirportState state = viewModel.getState();
        state.clearPreviousResults();
        state.setMessage(outputData.getDepartureErrorMessage());
        viewModel.firePropertyChanged("noFlightsError");
    }

    @Override
    public void prepareSuccessView(SearchByDepartureAirportOutputData outputData) {
        clearView(outputData);
        SearchByDepartureAirportState state = viewModel.getState();
        List<Flight> departureFlights = outputData.getDepartureFlights();
        if (departureFlights.isEmpty()) {
            state.setMessage("No flights available for this airport.");
            viewModel.firePropertyChanged("noFlightsError");
        } else {
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
        clearView(outputData); // Clear the previous state of the UI before showing the error message

        SearchByDepartureAirportState state = viewModel.getState();
        state.setMessage(outputData.getDepartureErrorMessage()); // Set the error message in the state
        viewModel.firePropertyChanged("noFlightsError");
    }

    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
