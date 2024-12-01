package usecase.search_by_arrival_airport;

import java.util.List;

import entities.Flight;

/**
 * Output Data for the Search By Airport Use Case.
 */
public class SearchByArrivalAirportOutputData {

    private final List<Flight> filteredArrivalFlights;
    private final String arrivalErrorMessage;

    public SearchByArrivalAirportOutputData(List<Flight> filteredArrivalFlights) {
        this.filteredArrivalFlights = filteredArrivalFlights;
        this.arrivalErrorMessage = null;
    }

    public SearchByArrivalAirportOutputData(String arrivalErrorMessage) {
        this.filteredArrivalFlights = null;
        this.arrivalErrorMessage = arrivalErrorMessage;
    }

    public final List<Flight> getFilteredArrivalFlights() {
        return filteredArrivalFlights;
    }

    public final String getArrivalErrorMessage() {
        return arrivalErrorMessage;
    }

    public final boolean isUseCaseFailed() {
        return arrivalErrorMessage != null;
    }
}
