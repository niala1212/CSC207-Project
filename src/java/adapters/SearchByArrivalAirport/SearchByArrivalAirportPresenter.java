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
        SearchByArrivalAirportState state = viewModel.getState();
        List<Flight> arrivalFlights = outputData.getFilteredArrivalFlights();
        if (outputData.isUseCaseFailed()) {
            // Case: No flights found
            state.setMessage(outputData.getArrivalErrorMessage());
            viewModel.firePropertyChanged("noFlightsError");
        } else {
            // Case: Flights found
            String airportName = arrivalFlights.get(0).getArrivalAirport();
            List<String> flightNumbers = extractFlightNumbers(arrivalFlights);

            state.setAirportName(airportName);
            state.setFlightNumbers(flightNumbers);
            state.setFlights(arrivalFlights);


            // Update the ViewModel with the full list of flights
            viewModel.firePropertyChanged("airportFlights");
        }
    }

    @Override
    public void prepareFailView(SearchByArrivalAirportOutputData outputData) {
        SearchByArrivalAirportState state = viewModel.getState();

        // Case: error occured
        state.setMessage(outputData.getArrivalErrorMessage());

        // Update the viewModel with the change property
        viewModel.firePropertyChanged("noFlightsError");
    }


    // Helper method to extract flight numbers
    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
