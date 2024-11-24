package use_case.SearchByFlightNumber;

import entities.Flight;

/**
 * The SearchByFlightNumber Interactor.
 */
public class SearchByFlightNumberInteractor implements SearchByFlightNumberInputBoundary {

    private final SearchByFlightNumberOutputBoundary searchByFlightNumberPresenter;

    public SearchByFlightNumberInteractor(SearchByFlightNumberOutputBoundary flightPresenter) {
        this.searchByFlightNumberPresenter = flightPresenter;
    }

    @Override
    public void execute(SearchByFlightNumberInputData searchByFlightNumberInputData) {
        String flightnumber = searchByFlightNumberInputData.getFlightNumber();
        SearchByFlightNumberDataAccessInterface flightDataAccessObject;
        //TODO populate flightDataAccessObject here

//        Flight foundFlight = flightDataAccessObject.getFlightByFlightNumber(flightnumber);
//
//        if (foundFlight != null) {
//            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData(foundFlight);
//            searchByFlightNumberPresenter.prepareSuccessView(outputData);
//        } else {
//            SearchByFlightNumberOutputData outputData = new SearchByFlightNumberOutputData("No such flights located");
//            searchByFlightNumberPresenter.prepareFailView(outputData);
//        }
    }
}
