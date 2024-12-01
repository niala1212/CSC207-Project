package usecase.SeeWorldMap;

/**
 * Output Boundary for the See world map use case.
 */
public interface SeeWorldMapOutputBoundary {
    /**
     * Prepares the success view for the See world map use case.
     * @param outputData the output data
     */
    void prepareSuccessView(SeeWorldMapOutputData outputData);

    /**
     * Prepares the failure view for the See world map use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(SeeWorldMapOutputData errorMessage);
}
