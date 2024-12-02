package app.gui;

import javax.swing.JFrame;

import adapters.SearchAirportLanded.SearchAirportLandedController;
import adapters.SearchAirportLanded.SearchAirportLandedPresenter;
import adapters.SearchAirportLanded.SearchAirportLandedViewModel;
import adapters.see_world_map.SeeWorldMapController;
import adapters.see_world_map.SeeWorldMapPresenter;
import adapters.see_world_map.SeeWorldMapViewModel;
import adapters.search_by_airlineid.SearchByAirlineIDController;
import adapters.search_by_airlineid.SearchByAirlineIDPresenter;
import adapters.search_by_airlineid.SearchByAirlineIDViewModel;
import adapters.search_by_arrival_airport.SearchByArrivalAirportController;
import adapters.search_by_arrival_airport.SearchByArrivalAirportPresenter;
import adapters.search_by_arrival_airport.SearchByArrivalAirportViewModel;
import adapters.search_by_departure_airport.SearchByDepartureAirportController;
import adapters.search_by_departure_airport.SearchByDepartureAirportPresenter;
import adapters.search_by_departure_airport.SearchByDepartureAirportViewModel;
import adapters.search_by_flight_number.SearchByFlightNumberController;
import adapters.search_by_flight_number.SearchByFlightNumberPresenter;
import adapters.search_by_flight_number.SearchByFlightNumberViewModel;
import dataaccess.AllAPICalling;
import usecase.SearchAirportLanded.SearchAirportLandedDataAccessInterface;
import usecase.SearchAirportLanded.SearchAirportLandedInputBoundary;
import usecase.SearchAirportLanded.SearchAirportLandedInteractor;
import usecase.SearchAirportLanded.SearchAirportLandedOutputBoundary;
import usecase.see_world_map.SeeWorldMapDataAccessInterface;
import usecase.see_world_map.SeeWorldMapInputBoundary;
import usecase.see_world_map.SeeWorldMapInteractor;
import usecase.see_world_map.SeeWorldMapOutputBoundary;
import usecase.search_by_airlineid.SearchByAirlineIDDataAccessInterface;
import usecase.search_by_airlineid.SearchByAirlineIDInputBoundary;
import usecase.search_by_airlineid.SearchByAirlineIDInteractor;
import usecase.search_by_airlineid.SearchByAirlineIDOutputBoundary;
import usecase.search_by_arrival_airport.SearchByArrivalAirportDataAccessInterface;
import usecase.search_by_arrival_airport.SearchByArrivalAirportInputBoundary;
import usecase.search_by_arrival_airport.SearchByArrivalAirportInteractor;
import usecase.search_by_arrival_airport.SearchByArrivalAirportOutputBoundary;
import usecase.search_by_departure_airport.SearchByDepartureAirportDataAccessInterface;
import usecase.search_by_departure_airport.SearchByDepartureAirportInputBoundary;
import usecase.search_by_departure_airport.SearchByDepartureAirportInteractor;
import usecase.search_by_departure_airport.SearchByDepartureAirportOutputBoundary;
import usecase.search_by_flight_number.SearchByFlightNumberDataAccessInterface;
import usecase.search_by_flight_number.SearchByFlightNumberInputBoundary;
import usecase.search_by_flight_number.SearchByFlightNumberInteractor;
import usecase.search_by_flight_number.SearchByFlightNumberOutputBoundary;

/**
 * The Application class is responsible for initializing the different use cases and presenting the GUI.
 * It creates instances of various controllers, presenters, and view models and sets up the main menu UI.
 * Connects the use cases with their respective UI components
 */
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:SuppressWarnings",
    "checkstyle:ClassFanOutComplexity"})
public class Application {
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel = new SearchByFlightNumberViewModel();
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel = new SearchByAirlineIDViewModel();
    private final SearchByDepartureAirportViewModel searchByDepartureAirportViewModel =
            new SearchByDepartureAirportViewModel();
    private final SearchByArrivalAirportViewModel searchByArrivalAirportViewModel =
            new SearchByArrivalAirportViewModel();
    private final SearchAirportLandedViewModel searchAirportLandedViewModel = new SearchAirportLandedViewModel();
    private final SeeWorldMapViewModel seeWorldMapViewModel = new SeeWorldMapViewModel();

    public Application() {
        // Create use cases
        final SearchByFlightNumberController searchByFlightNumberController = createSearchByFlightNumberUseCase();
        final SearchByAirlineIDController searchByAirlineIDController = createSearchByAirlineIDUseCase();
        final SearchByDepartureAirportController searchByDepartureAirportController = createSearchByDepartureUseCase();
        final SearchByArrivalAirportController searchByArrivalAirportController = createSearchByArrivalAirportUseCase();
        final SearchAirportLandedController searchAirportLandedController = createSearchAirportLandedUseCase();
        final SeeWorldMapController seeWorldMapController = createSeeWorldMapUseCase();

        // Initialize menu
        JFrame menu = new Menu(
                searchByFlightNumberViewModel,
                searchByFlightNumberController,
                searchByAirlineIDViewModel,
                searchByAirlineIDController,
                searchByDepartureAirportViewModel,
                searchByDepartureAirportController,
                searchByArrivalAirportViewModel,
                searchByArrivalAirportController,
                searchAirportLandedViewModel,
                searchAirportLandedController,
                seeWorldMapViewModel,
                seeWorldMapController
        );
    }

    private SearchByFlightNumberController createSearchByFlightNumberUseCase() {
        SearchByFlightNumberOutputBoundary outputBoundary =
                new SearchByFlightNumberPresenter(searchByFlightNumberViewModel);
        SearchByFlightNumberDataAccessInterface dao = new AllAPICalling();
        SearchByFlightNumberInputBoundary interactor = new SearchByFlightNumberInteractor(dao, outputBoundary);
        return new SearchByFlightNumberController(interactor);
    }

    private SearchByAirlineIDController createSearchByAirlineIDUseCase() {
        SearchByAirlineIDOutputBoundary outputBoundary = new SearchByAirlineIDPresenter(searchByAirlineIDViewModel);
        SearchByAirlineIDDataAccessInterface dao = new AllAPICalling();
        SearchByAirlineIDInputBoundary interactor = new SearchByAirlineIDInteractor(dao, outputBoundary);
        return new SearchByAirlineIDController(interactor);
    }

    private SearchByDepartureAirportController createSearchByDepartureUseCase() {
        SearchByDepartureAirportOutputBoundary outputBoundary =
                new SearchByDepartureAirportPresenter(searchByDepartureAirportViewModel);
        SearchByDepartureAirportDataAccessInterface dao = new AllAPICalling();
        SearchByDepartureAirportInputBoundary interactor = new SearchByDepartureAirportInteractor(dao, outputBoundary);
        return new SearchByDepartureAirportController(interactor);
    }

    private SearchByArrivalAirportController createSearchByArrivalAirportUseCase() {
        SearchByArrivalAirportOutputBoundary outputBoundary =
                new SearchByArrivalAirportPresenter(searchByArrivalAirportViewModel);
        SearchByArrivalAirportDataAccessInterface dao = new AllAPICalling();
        SearchByArrivalAirportInputBoundary interactor = new SearchByArrivalAirportInteractor(dao, outputBoundary);
        return new SearchByArrivalAirportController(interactor);
    }

    private SearchAirportLandedController createSearchAirportLandedUseCase() {
        SearchAirportLandedOutputBoundary outputBoundary =
                new SearchAirportLandedPresenter(searchAirportLandedViewModel);
        SearchAirportLandedDataAccessInterface dao = new AllAPICalling();
        SearchAirportLandedInputBoundary interactor = new SearchAirportLandedInteractor(dao, outputBoundary);
        return new SearchAirportLandedController(interactor);
    }

    private SeeWorldMapController createSeeWorldMapUseCase() {
        SeeWorldMapOutputBoundary outputBoundary = new SeeWorldMapPresenter(seeWorldMapViewModel);
        SeeWorldMapDataAccessInterface dao = new AllAPICalling();
        SeeWorldMapInputBoundary interactor = new SeeWorldMapInteractor(dao, outputBoundary);
        return new SeeWorldMapController(interactor);
    }
}
