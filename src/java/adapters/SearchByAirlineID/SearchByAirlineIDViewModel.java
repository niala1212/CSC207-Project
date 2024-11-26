package adapters.SearchByAirlineID;

import adapters.AbstractViewModel;
import entities.Flight;
import java.util.List;

public class SearchByAirlineIDViewModel extends AbstractViewModel<SearchByAirlineIDState> {

    public SearchByAirlineIDViewModel() {
        super(State.SEARCHBYAIRLINEID); // Initial state when searching by airline ID
        setState(new SearchByAirlineIDState()); // Set the initial data state
    }
}

