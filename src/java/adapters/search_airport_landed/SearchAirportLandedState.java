package adapters.search_airport_landed;

import java.util.ArrayList;
import java.util.List;

import adapters.AbstractState;

/**
 * State containing list of flight states for Search Airport Landed use case.
 */
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

    /**
     * Add a flight state.
     * @param state flight state to add.
     */
    public final void addFlightState(FlightState state) {
        this.flightStates.add(state);
    }
}
