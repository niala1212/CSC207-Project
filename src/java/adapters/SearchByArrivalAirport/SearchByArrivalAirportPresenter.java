package adapters.SearchByArrivalAirport;

import use_case.SearchByArrivalAirport.SearchByArrivalAirportOutputBoundary;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportOutputData;
import entities.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for the Search By Airport Use Case.
 */
public class SearchByArrivalAirportPresenter implements SearchByArrivalAirportOutputBoundary {

    private SearchByArrivalAirportViewModel viewModel;

    public SearchByArrivalAirportPresenter(SearchByArrivalAirportViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchByArrivalAirportOutputData outputData) {
        List<Flight> arrivalFlights = outputData.getFilteredArrivalFlights();
        if (arrivalFlights == null || arrivalFlights.isEmpty()) {
            // Case: No flights found
            viewModel.updateNoFlightsFound("No flights found for the specified airline.");
        } else {
            // Case: Flights found
            String airportName = arrivalFlights.get(0).getArrivalAirport();
            List<String> flightNumbers = extractFlightNumbers(arrivalFlights);

            // Update the ViewModel with the full list of flights
            viewModel.updateFlights(airportName, flightNumbers, arrivalFlights);
        }
    }

    @Override
    public void prepareFailView(SearchByArrivalAirportOutputData outputData) {
        String errorMessage = outputData.getArrivalErrorMessage();
        System.out.println("Error retrieving arrival flights: " + errorMessage);
        viewModel.updateNoFlightsFound("An error occurred: " + outputData.getArrivalErrorMessage());
    }


    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
