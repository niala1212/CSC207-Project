package adapters.SearchByFlightNumber;

/**
 * The state for the Search By Number View Model.
 */
public class SearchByFlightNumberState {
    private String flightNumber = "";
    private String departureTime = "";
    private String arrivalTime = "";
    private String status = "";
    private String searchError;

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSearchError() {
        return searchError;
    }

    public String getDepartureTime() {return departureTime;}

    public String getArrivalTime() {return arrivalTime;}

    public String getStatus() {return status;}

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setStatus(String status) {this.status = status;}

    public void setDepartureTime(String DepartureTime) {this.departureTime = DepartureTime;}

    public void setArrivalTime(String ArrivalTime) {this.arrivalTime = ArrivalTime;}
}
