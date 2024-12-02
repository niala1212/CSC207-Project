package usecase.see_world_map;

import java.io.IOException;
import java.util.List;

import entities.Flight;

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
    public void execute(SeeWorldMapInputData seeWorldMapInputData) throws IOException {

        try {
            List<Flight> foundFlights = flightDataAccessObject.getRandomFlights();
            System.out.println(foundFlights);
            if (foundFlights == null) {
                // Critical failure: API or connection issue
                SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(
                        "Error retrieving the flight data, please try again");
                seeWorldMapPresenter.prepareFailView(outputData);
            }
            else {
                // Flight found: Success
                SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(foundFlights);
                seeWorldMapPresenter.prepareSuccessView(outputData);
            }
        }
        catch (IOException error) {
            // Critical failure: Program runtime error or file missing.
            SeeWorldMapOutputData outputData = new SeeWorldMapOutputData(
                    "Error with program runtime. Please check relevant files and try again");
            seeWorldMapPresenter.prepareFailView(outputData);
        }
    }
}
