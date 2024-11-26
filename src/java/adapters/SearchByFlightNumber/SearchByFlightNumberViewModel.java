package adapters.SearchByFlightNumber;

import adapters.AbstractViewModel;

public class SearchByFlightNumberViewModel extends AbstractViewModel<SearchByFlightNumberState> {

    public SearchByFlightNumberViewModel() {
        super(ViewState.SEARCHBYFLIGHT); // Set the initial view state
        setState(new SearchByFlightNumberState()); // Set the initial data state
    }
}

