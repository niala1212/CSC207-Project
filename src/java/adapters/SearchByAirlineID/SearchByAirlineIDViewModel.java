package adapters.SearchByAirlineID;

import adapters.AbstractViewModel;
import entities.Flight;
import java.util.List;

/**
 * The Search By Airline ID View Model.
 */
public class SearchByAirlineIDViewModel extends AbstractViewModel<SearchByAirlineIDState> {

    public SearchByAirlineIDViewModel() {
        super(ViewState.SEARCHBYAIRLINEID); // Initial state when searching by airline ID
        setState(new SearchByAirlineIDState()); // Set the initial data state
    }
}

