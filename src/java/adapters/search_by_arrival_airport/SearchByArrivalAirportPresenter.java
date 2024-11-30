package adapters.search_by_arrival_airport;

import java.util.ArrayList;
import java.util.List;

import entities.Flight;
import usecase.search_by_arrival_airport.SearchByArrivalAirportOutputBoundary;
import usecase.search_by_arrival_airport.SearchByArrivalAirportOutputData;

/**
 * Presenter for the Search By Arrival Airport Use Case.
 */
public class SearchByArrivalAirportPresenter implements SearchByArrivalAirportOutputBoundary {

    private SearchByArrivalAirportViewModel viewModel;

    public SearchByArrivalAirportPresenter(SearchByArrivalAirportViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchByArrivalAirportOutputData outputData) {
        // Making the state from the viewModel
        SearchByArrivalAirportState state = viewModel.getState();
        List<Flight> arrivalFlights = outputData.getFilteredArrivalFlights();
        if (outputData.isUseCaseFailed()) {
            // Case: No flights found when Use Case fails
            // Sets the state to an arrival error message and fires a property change
            state.setMessage(outputData.getArrivalErrorMessage());
            viewModel.firePropertyChanged("noFlightsError");
        }
        else {
            // Case: Flights found
            // Preparing the success view by setting the state with the arrival flights
            String airportName = arrivalFlights.get(0).getArrivalAirport();
            List<String> flightNumbers = extractFlightNumbers(arrivalFlights);

            state.setAirportName(airportName);
            state.setFlightNumbers(flightNumbers);
            state.setFlights(arrivalFlights);

            // Update the ViewModel with the full list of arrival flights by firing property change
            viewModel.firePropertyChanged("airportFlights");
        }
    }

    @Override
    public void prepareFailView(SearchByArrivalAirportOutputData outputData) {
        SearchByArrivalAirportState state = viewModel.getState();

        // Case: error occured
        state.setMessage(outputData.getArrivalErrorMessage());

        // Update the viewModel with the property change
        viewModel.firePropertyChanged("noFlightsError");
    }

    // Helper method to extract flight numbers in the list of flights
    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
