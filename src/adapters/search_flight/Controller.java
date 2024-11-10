package adapters;

public class SearchController {

    private final SearchFlightUseCase searchFlightUseCase:
    private final FlightPresenter presenter:

    public SearchController(SearchFlightUseCase searchFlightUseCase, FlightPresenter presenter) {
        this.searchFlightUseCase = searchFlightUseCase;
        this.presenter = presenter;
    }

    public String search(String input) {
        Flight flight = searchFlightUseCase.searchByFlightNumber(input);
        if (flight != null) {
            return presenter.presentFlightDetails(flight);
        } else {
            return presenter.presentFlightNotFound(input);
        }
    }
}
