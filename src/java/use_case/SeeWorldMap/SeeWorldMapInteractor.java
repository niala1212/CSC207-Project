package use_case.SeeWorldMap;

import entities.Flight;
import java.util.List;

/**
 * The SeeWorldMap Interactor.
 */
public class SeeWorldMapInteractor implements SeeWorldMapInputBoundary {

    private final SeeWorldMapDataAccessInterface flightDataAccessObject;
    private final SeeWorldMapOutputBoundary seeWorldMapPresenter;

    public SeeWorldMapInteractor(SeeWorldMapDataAccessInterface flightDataAccessObject,
                                 SeeWorldMapOutputBoundary seeWorldMapPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.seeWorldMapPresenter = seeWorldMapPresenter;
    }

    @Override
    public void execute(SeeWorldMapInputData seeWorldMapInputData) {

        try {
            List<Flight> foundFlights = flightDataAccessObject.getRandomFlights();
            System.out.println(foundFlights);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(
                        "Error retrieving the flight data, please try again");
                seeWorldMapPresenter.prepareFailView(outputData);
            } else {
                // Flight found: Success
                SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(foundFlights);
                seeWorldMapPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(
                    "An unexpected error occurred: " + e.getMessage());
            seeWorldMapPresenter.prepareFailView(outputData);
        }
    }
}
