package adapters.SeeWorldMap;

import usecase.SeeWorldMap.SeeWorldMapInputBoundary;
import usecase.SeeWorldMap.SeeWorldMapInputData;


public class SeeWorldMapController {
    private final SeeWorldMapInputBoundary seeWorldMapInteractor;

    public SeeWorldMapController(SeeWorldMapInputBoundary seeWorldMapInteractor) {
        this.seeWorldMapInteractor = seeWorldMapInteractor;
    }

    /**
     * Executes the see world map use case.
     */
//    MAKE SURE TO FIX THE NULL THING
    public void execute() {
        final SeeWorldMapInputData inputData = new SeeWorldMapInputData("");
        this.seeWorldMapInteractor.execute(inputData);
    }
}