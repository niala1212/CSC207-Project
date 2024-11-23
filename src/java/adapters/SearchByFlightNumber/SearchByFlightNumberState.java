package adapters.SearchByFlightNumber;

/**
 * The state for the Search By Number View Model.
 */
public class SearchByFlightNumberState {
    private String flightNumber = "";
    private String searchError;

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }
}
