package use_case.SearchByArrivalAirport;

import entities.Flight;

import java.util.List;

/**
 * The Search by Airport Interactor.
 */
public class SearchByArrivalAirportInteractor implements SearchByArrivalAirportInputBoundary {

    private final SearchByArrivalAirportDataAccessInterface flightDataAccessObject;
    private final SearchByArrivalAirportOutputBoundary searchByArrivalAirportPresenter;

    public SearchByArrivalAirportInteractor(SearchByArrivalAirportDataAccessInterface flightDataAccessObject,
                                            SearchByArrivalAirportOutputBoundary searchByArrivalAirportPresenter) {
        this.flightDataAccessObject = flightDataAccessObject;
        this.searchByArrivalAirportPresenter = searchByArrivalAirportPresenter;
    }

    @Override
    public void execute(SearchByArrivalAirportInputData searchByAirportInputData) {
        String airportCode = searchByAirportInputData.getAirportCode();

        try {
            List<Flight> arrivalFlights = flightDataAccessObject.getArrivalFlights(airportCode);

            if (arrivalFlights == null) {
                // Critical failure: API or connection issue
                SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData("Error retrieving flight data. Please try again later.");
                searchByArrivalAirportPresenter.prepareFailView(outputData);
            } else if (arrivalFlights.isEmpty()){
                // No flights found: Valid but empty result
                SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData("No arrival flights found for the specified airport.");
                searchByArrivalAirportPresenter.prepareSuccessView(outputData);
            } else{
                // Flights found: Success
                SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData(arrivalFlights);
                searchByArrivalAirportPresenter.prepareSuccessView(outputData);
            }
        } catch (Exception e) {
            // Unexpected failure: Handle exceptions
            SearchByArrivalAirportOutputData outputData = new SearchByArrivalAirportOutputData("An unexpected error occurred");
            searchByArrivalAirportPresenter.prepareFailView(outputData);
        }
    }
}
