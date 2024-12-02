package adapters.search_airport_landed;

import adapters.AbstractState;

import java.util.ArrayList;
import java.util.List;

public class SearchAirportLandedState {
    private String error;
    private List<AbstractState> flightStates = new ArrayList<AbstractState>();

    public final String getError() {
        return error;
    }

    public final void setError(String error) {
        this.error = error;
    }

    public final List<AbstractState> getFlightStates() {
        return flightStates;
    }

    public final void addFlightState(FlightState state) {
        this.flightStates.add(state);
    }
}
