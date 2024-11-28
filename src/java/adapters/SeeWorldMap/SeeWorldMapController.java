package adapters.SeeWorldMap;

import use_case.SeeWorldMap.SeeWorldMapInputBoundary;
import use_case.SeeWorldMap.SeeWorldMapInputData;


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
        final SeeWorldMapInputData inputData = new SeeWorldMapInputData(null);
        this.seeWorldMapInteractor.execute(inputData);
    }
}