package adapters.SearchAirportLanded;

import adapters.AbstractViewModel;

public class SearchAirportLandedViewModel extends AbstractViewModel<SearchAirportLandedState> {

    public SearchAirportLandedViewModel() {
        super(ViewState.SEARCHBYFLIGHT); // Set the initial view state
        setState(new SearchAirportLandedState()); // Set the initial data state
    }
}