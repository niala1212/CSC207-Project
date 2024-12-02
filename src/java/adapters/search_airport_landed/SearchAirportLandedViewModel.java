package adapters.search_airport_landed;

import adapters.AbstractViewModel;

public class SearchAirportLandedViewModel extends AbstractViewModel<SearchAirportLandedState> {

    public SearchAirportLandedViewModel() {
        super(ViewState.SEARCHBYAIRPORTLANDED); // Set the initial view state
        setState(new SearchAirportLandedState()); // Set the initial data state
    }
}
