package adapters.SearchByArrivalAirport;

import adapters.AbstractViewModel;
import entities.Flight;
import java.util.List;

public class SearchByArrivalAirportViewModel extends AbstractViewModel<SearchByArrivalAirportState> {

    public SearchByArrivalAirportViewModel() {
        super(ViewState.SEARCHBYAIRPORTID); // Initial state when searching by airport
     setState(new SearchByArrivalAirportState()); // Set initial state
    }
}
