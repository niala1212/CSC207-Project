package adapters.search_by_arrival_airport;

import adapters.AbstractViewModel;

/**
 * ViewModel for the Search By Arrival Airport Use Case.
 */
public class SearchByArrivalAirportViewModel extends AbstractViewModel<SearchByArrivalAirportState> {

    public SearchByArrivalAirportViewModel() {
        // This is the nitial state when searching by arrival airport
        super(ViewState.SEARCHBYAIRPORTID);

        // Seting the initial state
        setState(new SearchByArrivalAirportState());
    }
}
