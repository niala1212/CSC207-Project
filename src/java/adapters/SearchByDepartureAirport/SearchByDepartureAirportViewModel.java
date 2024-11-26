package adapters.SearchByDepartureAirport;

import adapters.AbstractViewModel;

public class SearchByDepartureAirportViewModel extends AbstractViewModel<SearchByDepartureAirportState> {

    public SearchByDepartureAirportViewModel() {
        super(ViewState.SEARCHBYAIRPORTID);
        setState(new SearchByDepartureAirportState());
    }
}
