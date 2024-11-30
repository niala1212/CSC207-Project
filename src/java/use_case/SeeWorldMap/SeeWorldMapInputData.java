package use_case.SeeWorldMap;

/**
 * The input data for the SeeWorldMap Use Case.
 * IDK what this is gonna be used for yet because there are no input bits yet
 */
public class SeeWorldMapInputData {
    private final String reset;

    public SeeWorldMapInputData(String reset) {
        this.reset = reset != null ? reset : "";  // Default to empty string if null
    }

    public String getReset() {
        return reset;
    }
}
