package adapters.search_airport_landed;

import java.util.List;

import usecase.search_airport_landed.FlightOutputData;
import usecase.search_airport_landed.SearchAirportLandedOutputBoundary;
import usecase.search_airport_landed.SearchAirportLandedOutputData;

/**
 * The Search by Landed Airport Presenter.
 */
public class SearchAirportLandedPresenter implements SearchAirportLandedOutputBoundary {

    private SearchAirportLandedViewModel viewModel;

    public SearchAirportLandedPresenter(SearchAirportLandedViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(SearchAirportLandedOutputData outputData) {
        // Making the state from the viewModel
        SearchAirportLandedState searchAirportLandedState = viewModel.getState();
        List<FlightOutputData> filteredFlights = outputData.getFlightOutputData();
        for (FlightOutputData flightOutputData : filteredFlights) {
            FlightState flightState = new FlightState();
            flightState.setFlightNumber(flightOutputData.getFlightNumber());
            flightState.setDepartureTime(flightOutputData.getDepartureTime());
            flightState.setArrivalTime(flightOutputData.getArrivalTime());
            flightState.setArrivalAirport(flightOutputData.getArrivalAirport());
            flightState.setDepartureAirport(flightOutputData.getDepartureAirport());
            flightState.setStatus(flightOutputData.getStatus());

            searchAirportLandedState.addFlightState(flightState);
        }
        viewModel.firePropertyChanged("landedFlightDetails");
    }

    @Override
    public void prepareFailView(SearchAirportLandedOutputData outputData) {
        SearchAirportLandedState state = viewModel.getState();

        // Case: error occured
        state.setError(outputData.getErrorMessage());

        // Update the viewModel with the property change
        viewModel.firePropertyChanged("landedFlightError");
    }
}
