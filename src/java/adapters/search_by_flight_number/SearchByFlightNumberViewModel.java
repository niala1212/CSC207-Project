package adapters.search_by_flight_number;

import adapters.AbstractViewModel;

/**
 * ViewModel for the Search By Flight Number Use Case.
 */
public class SearchByFlightNumberViewModel extends AbstractViewModel<SearchByFlightNumberState> {

    public SearchByFlightNumberViewModel() {
        // Set the initial view state when searching by flight number
        super(ViewState.SEARCHBYFLIGHT);

        // Set the initial data state
        setState(new SearchByFlightNumberState());
    }
}

