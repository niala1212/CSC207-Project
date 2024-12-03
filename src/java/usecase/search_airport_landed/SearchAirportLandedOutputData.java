package usecase.search_airport_landed;

import java.util.ArrayList;
import java.util.List;

import entities.Flight;

/**
 * Output Data for the Search By Landed Flights for an Airport Use Case.
 */
public class SearchAirportLandedOutputData {

    private final List<Flight> flightList;
    private final List<FlightOutputData> flightOutputDataList;
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchAirportLandedOutputData(List<Flight> flightList) {
        this.flightList = flightList;
        if (flightList != null) {
            flightOutputDataList = new ArrayList<>();
            for (Flight flight : flightList) {
                this.flightOutputDataList.add(new FlightOutputData(flight));
            }
        }
        else {
            flightOutputDataList = null;
        }
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchAirportLandedOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
        flightList = null;
        flightOutputDataList = null;
    }

    public final List<FlightOutputData> getFlightOutputDataList() {
        return flightOutputDataList;
    }

    public final List<Flight> getFlightList() {
        return flightList;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public final boolean isUseCaseFailed() {
        return errorMessage != null;
    }
}
