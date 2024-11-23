//package adapters.SearchByFlightNumber;
//
//import adapters.ViewManagerModel;
//import adapters.search.SearchViewModel;
//import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;
//import use_case.SearchByFlightNumber.SearchByFlightNumberOutputData;
//
///**
// * The Presenter for the Search By Flight Number Use Case.
// */
//public class SearchByFlightNumberPresenter implements SearchByFlightNumberOutputBoundary {
//
//    private final SearchViewModel searchViewModel;
//    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel;
//    private final ViewManagerModel viewManagerModel;
//
//    public SearchByFlightNumberPresenter(ViewManagerModel viewManagerModel,
//                                         SearchViewModel searchViewModel,
//                                         SearchByFlightNumberViewModel searchByFlightNumberViewModel) {
//        this.viewManagerModel = viewManagerModel;
//        this.searchViewModel = searchViewModel;
//        this.searchByFlightNumberViewModel = searchByFlightNumberViewModel;
//    }
//
//    @Override
//    public void prepareSuccessView(SearchByFlightNumberOutputData response) {
//        // 1. Clear search-related errors
//        searchViewModel.setError(""); // Reset any prior errors.
//
//        // 2. Update the FlightDetailsViewModel with data from the response
//        searchByFlightNumberViewModel.setFlightNumber(response.getFlightNumber());
//        searchByFlightNumberViewModel.setDepartureTime(response.getDepartureTime());
//        searchByFlightNumberViewModel.setArrivalTime(response.getArrivalTime());
//        searchByFlightNumberViewModel.setStatus(response.getStatus());
//        searchByFlightNumberViewModel.firePropertyChanged(); // Notify View to update.
//
//        // 3. Navigate to the Flight Details View
//        viewManagerModel.setState(searchByFlightNumberViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        // Update the SearchViewModel to show the error message.
//        searchViewModel.setError(error);
//        searchViewModel.firePropertyChanged(); // Notify View to update.
//    }
//}