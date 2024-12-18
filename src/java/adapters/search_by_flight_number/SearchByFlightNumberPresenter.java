package adapters.search_by_flight_number;

import usecase.search_by_flight_number.SearchByFlightNumberOutputBoundary;
import usecase.search_by_flight_number.SearchByFlightNumberOutputData;

/**
 * The Presenter for the Search By Flight Number Use Case.
 */
public class SearchByFlightNumberPresenter implements SearchByFlightNumberOutputBoundary {

    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;

    public SearchByFlightNumberPresenter(SearchByFlightNumberViewModel searchByFlightNumberViewModel) {
        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
    }

    @Override
    public void prepareSuccessView(SearchByFlightNumberOutputData response) {
        // Making the state from the viewModel
        SearchByFlightNumberState state = searchByFlightNumberViewModel.getState();

        // Update the SearchByFlightNumberState with data from the response
        state.setFlightNumber(response.getFlightNumber());
        state.setDepartureTime(response.getDepartureTime());
        state.setArrivalTime(response.getArrivalTime());
        state.setArrivalAirport(response.getArrivalAirport());
        state.setDepartureAirport(response.getDepartureAirport());
        state.setStatus(response.getStatus());

        // Notify ViewModel to update the flight details by firing property change
        searchByFlightNumberViewModel.firePropertyChanged("flightDetails");
    }

    @Override
    public void prepareFailView(SearchByFlightNumberOutputData error) {
        // Update the SearchViewModel to show error message if failure
        SearchByFlightNumberState state = searchByFlightNumberViewModel.getState();
        state.setError(error.getErrorMessage());

        // Notify ViewModel to update the error message
        searchByFlightNumberViewModel.firePropertyChanged("error");
    }
}
