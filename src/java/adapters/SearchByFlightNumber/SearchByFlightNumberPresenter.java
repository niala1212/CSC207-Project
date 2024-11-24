package adapters.SearchByFlightNumber;

import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputData;

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
        SearchByFlightNumberState state = searchByFlightNumberViewModel.getState();

        // 2. Update the SearchByFlightNumberState with data from the response
//        state.setFlightNumber(response.getFlightNumber());
//        state.setDepartureTime(response.getDepartureTime());
//        state.setArrivalTime(response.getArrivalTime());
//        state.setStatus(response.getStatus());
        state.setArrivalTime("9:15");
        state.setDepartureTime("12:15");
        state.setStatus("OK");

        // Notify View to update the flight details
        searchByFlightNumberViewModel.firePropertyChanged("flightDetails");

    }

    @Override
    public void prepareFailView(SearchByFlightNumberOutputData error) {
        // 1. Update the SearchViewModel to show the error message
        SearchByFlightNumberState state = searchByFlightNumberViewModel.getState();
//        state.setSearchError(error);
        SearchByFlightNumberOutputData message = new SearchByFlightNumberOutputData("No flight");
        state.setSearchError(message);
        searchByFlightNumberViewModel.firePropertyChanged("error"); // Notify View to update the error message
    }
}
