package adapters.SearchByDepartureAirport;

import adapters.SearchByArrivalAirport.SearchByArrivalAirportState;
import entities.Flight;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for the Departures by Airport use case
 */
public class SearchByDepartureAirportPresenter implements SearchByDepartureAirportOutputBoundary {

    private SearchByDepartureAirportViewModel viewModel;

    public SearchByDepartureAirportPresenter(SearchByDepartureAirportViewModel searchByDepartureAirportViewModel) {
        this.viewModel = searchByDepartureAirportViewModel;
    }

    /**
     * Prepares the success view for the Search by Airport use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SearchByDepartureAirportOutputData outputData) {
        SearchByDepartureAirportState state = viewModel.getState();
        List<Flight> departureFlights = outputData.getDepartureFlights();
        if (outputData.isUseCaseFailed()) {
            // Case: No flights found
            state.setMessage(outputData.getDepartureErrorMessage());
            viewModel.firePropertyChanged("noFlightsError");
        } else {
            // Case: Flights found
            String airportName = departureFlights.get(0).getDepartureAirport();
            List<String> flightNumbers = extractFlightNumbers(departureFlights);

            state.setAirportName(airportName);
            state.setFlightNumbers(flightNumbers);
            state.setFlights(departureFlights);


            // Update the ViewModel with the full list of flights
            viewModel.firePropertyChanged("airportFlights");
        }
    }

    /**
     * Prepares the failure view for the Search by Airport use case.
     *
     * @param outputData the explanation of the failure
     */
    @Override
    public void prepareFailView(SearchByDepartureAirportOutputData outputData) {
        SearchByDepartureAirportState state = viewModel.getState();
        state.setMessage(outputData.getDepartureErrorMessage());
        viewModel.firePropertyChanged("noFlightsError");
    }

    private List<String> extractFlightNumbers(List<Flight> flights) {
        List<String> flightNumbers = new ArrayList<>();
        for (Flight flight : flights) {
            flightNumbers.add(flight.getFlightNumber());
        }
        return flightNumbers;
    }
}
