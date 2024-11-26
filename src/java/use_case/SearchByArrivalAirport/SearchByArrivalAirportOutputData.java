package use_case.SearchByArrivalAirport;

import entities.Flight;
import java.util.List;

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


    public List<Flight> getFilteredArrivalFlights() {
        return filteredArrivalFlights;
    }

    public String getArrivalErrorMessage() {
        return arrivalErrorMessage;
    }


    public boolean isUseCaseFailed() {
        return arrivalErrorMessage != null;
    }
}
