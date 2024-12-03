package adapters.see_world_map;

import java.io.IOException;

import usecase.see_world_map.SeeWorldMapInputBoundary;
import usecase.see_world_map.SeeWorldMapInputData;

public class SeeWorldMapController {
    private final SeeWorldMapInputBoundary seeWorldMapInteractor;

    public SeeWorldMapController(SeeWorldMapInputBoundary seeWorldMapInteractor) {
        this.seeWorldMapInteractor = seeWorldMapInteractor;
    }

    /**
     * Executes the see world map use case.
     * @throws IOException in the event of an error
     */
    public void execute() throws IOException {
        final SeeWorldMapInputData inputData = new SeeWorldMapInputData("");
        this.seeWorldMapInteractor.execute(inputData);
    }
}
