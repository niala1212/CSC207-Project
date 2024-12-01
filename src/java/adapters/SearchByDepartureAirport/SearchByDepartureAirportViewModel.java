package adapters.SearchByDepartureAirport;

import adapters.AbstractViewModel;

/**
 * View Model for the Search By Departure use case.
 */
public class SearchByDepartureAirportViewModel extends AbstractViewModel<SearchByDepartureAirportState> {

    public SearchByDepartureAirportViewModel() {
        super(ViewState.SEARCHBYAIRPORTID);
        setState(new SearchByDepartureAirportState());
    }
}
