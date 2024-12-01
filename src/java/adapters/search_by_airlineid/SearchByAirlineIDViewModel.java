package adapters.search_by_airlineid;

import adapters.AbstractViewModel;

/**
 * The Search By Airline ID View Model.
 */
public class SearchByAirlineIDViewModel extends AbstractViewModel<SearchByAirlineIDState> {

    public SearchByAirlineIDViewModel() {
        // This is the initial state when searching by airline ID
        super(ViewState.SEARCHBYAIRLINEID);

        // Seting the initial data state
        setState(new SearchByAirlineIDState());
    }
}

