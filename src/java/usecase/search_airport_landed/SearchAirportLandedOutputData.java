package usecase.search_airport_landed;

import entities.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Data for the Search By Airport Use Case.
 */
public class SearchAirportLandedOutputData {

    private final List<FlightOutputData> flightOutputDataList = new ArrayList<FlightOutputData>();
    private final String errorMessage;

    // Constructor for success (with filtered flights)
    public SearchAirportLandedOutputData(List<Flight> filteredFlights) {
        for (Flight flight : filteredFlights) {
            this.flightOutputDataList.add(new FlightOutputData(flight));
        }
        this.errorMessage = null;
    }

    // Constructor for failure (when no flights are found or an error occurs)
    public SearchAirportLandedOutputData(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<FlightOutputData> getFlightOutputData() {
        return flightOutputDataList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isUseCaseFailed() {
        return errorMessage != null;
    }
}
