package adapters.search_airport_landed;

import adapters.AbstractViewModel;

/**
 * View Model for Search Airport Landed use case.
 */
public class SearchAirportLandedViewModel extends AbstractViewModel<SearchAirportLandedState> {

    public SearchAirportLandedViewModel() {
        // Set the initial view state
        super(ViewState.SEARCHBYAIRPORTLANDED);
        // Set the initial data state
        setState(new SearchAirportLandedState());
    }
}
