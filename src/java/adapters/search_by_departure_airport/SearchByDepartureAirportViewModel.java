package adapters.search_by_departure_airport;

import adapters.AbstractViewModel;

/**
 * View Model for the Search By Departure use case.
 */
public class SearchByDepartureAirportViewModel extends AbstractViewModel<SearchByDepartureAirportState> {

    public SearchByDepartureAirportViewModel() {
        super(ViewState.SEARCHBYDEPARTUREAIRPORT);
        setState(new SearchByDepartureAirportState());
    }
}
