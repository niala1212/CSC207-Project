package use_case.SearchByFlightNumber;

import entities.Flight;
import java.time.LocalDate;

/**
 * The SearchByFlightNumber Interactor.
 */
public class SearchByFlightNumberInteractor implements SearchByFlightNumberInputBoundary {

    private final SearchByFlightNumberDataAccessInterface flightDataAccessObject;
    private final SearchByFlightNumberOutputBoundary searchByFlightNumberPresenter;

    public SearchByFlightNumberInteractor(SearchByFlightNumberDataAccessInterface flightDataAccessObject,
                                       SearchByFlightNumberOutputBoundary searchByFlightNumberPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByFlightNumberPresenter = searchByFlightNumberPresenter;
    }

    @Override
    public void execute(SearchByFlightNumberInputData searchByFlightNumberInputData) {
        String flightnumber = searchByFlightNumberInputData.getFlightNumber();

        try {
            Flight foundFlight = flightDataAccessObject.getFlightByFlightNumber(flightnumber,
                    LocalDate.now().toString());
            if (foundFlight == null) {
                // Critical failure: API or connection issue
                SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(
                        "Error retrieving the requested flight data. Please try again later.");
                searchByFlightNumberPresenter.prepareFailView(outputData);
            } else {
                // Flight found: Success
                SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(foundFlight);
                searchByFlightNumberPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(
                    "An unexpected error occurred: " + e.getMessage());
            searchByFlightNumberPresenter.prepareFailView(outputData);
        }
    }
}
