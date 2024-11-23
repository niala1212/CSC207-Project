package adapters.SearchByFlightNumber;

import adapters.ViewModel;

public class SearchByFlightNumberViewModel extends ViewModel<SearchByFlightNumberState> {

    public SearchByFlightNumberViewModel() {
        super(State.SEARCHBYFLIGHT); // Set the initial view state
        setState(new SearchByFlightNumberState()); // Set the initial data state
    }
}

