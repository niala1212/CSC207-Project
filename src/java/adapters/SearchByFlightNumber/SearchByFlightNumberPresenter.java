package adapters.SearchByFlightNumber;

import adapters.ViewManagerModel;
import adapters.search.SearchViewModel;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputData;

/**
 * The Presenter for the Search By Flight Number Use Case.
 */
public class SearchByFlightNumberPresenter implements SearchByFlightNumberOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchByFlightNumberPresenter(ViewManagerModel viewManagerModel,
                                         SearchViewModel searchViewModel,
                                         SearchByFlightNumberViewModel searchByFlightNumberViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
    }

    @Override
    public void prepareSuccessView(SearchByFlightNumberOutputData response) {
        // 1. Clear search-related errors in the search view model
        searchViewModel.setError("");
        searchViewModel.firePropertyChanged(); // Reset any prior errors.

        // 2. Update the SearchByFlightNumberState with data from the response
        SearchByFlightNumberState state = searchByFlightNumberViewModel.getState();
        state.setFlightNumber(response.getFlightNumber());
        state.setDepartureTime(response.getDepartureTime());
        state.setArrivalTime(response.getArrivalTime());
        state.setStatus(response.getStatus());

        // Notify View to update the flight details
        searchByFlightNumberViewModel.firePropertyChanged("flightDetails");

        // 3. Navigate to the Flight Details View
        viewManagerModel.setState(searchByFlightNumberViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SearchByFlightNumberOutputData error) {
        // 1. Update the SearchViewModel to show the error message
        searchViewModel.setError(error);
        searchViewModel.firePropertyChanged(); // Notify View to update the error message
    }
}
