package adapters.SearchAirportArrivals;

import adapters.AbstractViewModel;

public class SearchAirportArrivalsViewModel extends AbstractViewModel<SearchAirportArrivalsState> {

    public SearchAirportArrivalsViewModel() {
        super(ViewState.SEARCHBYFLIGHT); // Set the initial view state
        setState(new SearchAirportArrivalsState()); // Set the initial data state
    }
}
