package usecase.see_world_map;

import java.io.IOException;

/**
 * Input Boundary for actions which are related to Seeing the world map.
 */
public interface SeeWorldMapInputBoundary {

    /**
     * Execute the SeeWorldMap Use Case.
     * @param SeeWorldMapInputData the input data for this use case
     * @throws IOException in the event of an error
     */
    void execute(SeeWorldMapInputData SeeWorldMapInputData) throws IOException;
}
