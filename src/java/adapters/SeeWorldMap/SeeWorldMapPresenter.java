package adapters.SeeWorldMap;

import use_case.SeeWorldMap.SeeWorldMapOutputBoundary;
import use_case.SeeWorldMap.SeeWorldMapOutputData;

/**
 * The Presenter for the Search By Flight Number Use Case.
 */
public class SeeWorldMapPresenter implements SeeWorldMapOutputBoundary {

    private final SeeWorldMapViewModel seeWorldMapViewModel;

    public SeeWorldMapPresenter(SeeWorldMapViewModel seeWorldMapViewModel) {
        this.seeWorldMapViewModel = seeWorldMapViewModel;
    }

    @Override
    public void prepareSuccessView(SeeWorldMapOutputData response) {
        SeeWorldMapState state = seeWorldMapViewModel.getState();

        // Update the SearchByFlightNumberState with data from the response
//        state.setFlightNumber(response.getFlightNumber());
//        state.setDepartureTime(response.getDepartureTime());
//        state.setArrivalTime(response.getArrivalTime());
//        state.setStatus(response.getStatus());

        // Notify View to update the flight details
        seeWorldMapViewModel.firePropertyChanged("flightDetails");
    }

    @Override
    public void prepareFailView(SeeWorldMapOutputData error) {
        // Update the SearchViewModel to show the error message
        SeeWorldMapState state = seeWorldMapViewModel.getState();
        state.setSearchError(error.getErrorMessage());
        // Notify View to update the error message
        seeWorldMapViewModel.firePropertyChanged("error");
    }

}
