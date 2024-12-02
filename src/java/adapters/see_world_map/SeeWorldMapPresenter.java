package adapters.see_world_map;

import java.awt.geom.Point2D;
import java.util.Map;

import entities.Flight;
import usecase.see_world_map.SeeWorldMapOutputBoundary;
import usecase.see_world_map.SeeWorldMapOutputData;

/**
 * The Presenter for the SeeWorldMap Use Case.
 */
public class SeeWorldMapPresenter implements SeeWorldMapOutputBoundary {

    private final SeeWorldMapViewModel seeWorldMapViewModel;

    public SeeWorldMapPresenter(SeeWorldMapViewModel seeWorldMapViewModel) {
        this.seeWorldMapViewModel = seeWorldMapViewModel;
    }

    @Override
    public void prepareSuccessView(SeeWorldMapOutputData response) {
        SeeWorldMapState state = seeWorldMapViewModel.getState();

        state.clearMarkers();

        Map<Point2D.Double, Flight> flightData = response.getFlightMarkers();
        for (Map.Entry<Point2D.Double, Flight> entry : flightData.entrySet()) {
            state.addMarker(entry.getKey(), entry.getValue());
        }

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
