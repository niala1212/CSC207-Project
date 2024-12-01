package usecase.SeeWorldMap;

/**
 * Input Boundary for actions which are related to Seeing the world map.
 */
public interface SeeWorldMapInputBoundary {

    /**
     * Execute the SeeWorldMap Use Case.
     * @param SeeWorldMapInputData the input data for this use case
     */
    void execute(SeeWorldMapInputData SeeWorldMapInputData);
}
