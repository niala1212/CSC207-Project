package usecase.see_world_map;

import java.util.Objects;

/**
 * The input data for the SeeWorldMap Use Case.
 * This class passes on no current information as there is no required info.
 * In the future, one can extend this by added a counter for how may api calls should be made (for a larger dataset).
 */
public class SeeWorldMapInputData {
    private final String reset;

    public SeeWorldMapInputData(String reset) {
        this.reset = Objects.requireNonNullElse(reset, "");
    }

    /**
     * Unused method.
     * @return the amount of times the api should be reset.
     */
    public String getReset() {
        return reset;
    }
}
