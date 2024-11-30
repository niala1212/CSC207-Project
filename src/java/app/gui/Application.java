package app.gui;

import adapters.search_by_arrival_airport.SearchByArrivalAirportController;
import adapters.search_by_arrival_airport.SearchByArrivalAirportPresenter;
import adapters.search_by_arrival_airport.SearchByArrivalAirportViewModel;
import adapters.SearchAirportLanded.SearchAirportLandedController;
import adapters.SearchAirportLanded.SearchAirportLandedPresenter;
import adapters.SearchAirportLanded.SearchAirportLandedViewModel;
import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDPresenter;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportController;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportPresenter;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportViewModel;
import adapters.search_by_flight_number.SearchByFlightNumberController;
import adapters.search_by_flight_number.SearchByFlightNumberPresenter;
import adapters.search_by_flight_number.SearchByFlightNumberViewModel;
import adapters.SeeWorldMap.SeeWorldMapController;
import adapters.SeeWorldMap.SeeWorldMapPresenter;
import adapters.SeeWorldMap.SeeWorldMapViewModel;
import data_access.All_API_Calling;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportDataAccessInterface;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportInputBoundary;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportInteractor;
import use_case.SearchByArrivalAirport.SearchByArrivalAirportOutputBoundary;
import use_case.SearchAirportLanded.SearchAirportLandedDataAccessInterface;
import use_case.SearchAirportLanded.SearchAirportLandedInputBoundary;
import use_case.SearchAirportLanded.SearchAirportLandedInteractor;
import use_case.SearchAirportLanded.SearchAirportLandedOutputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import use_case.SearchByAirlineID.SearchByAirlineIDInputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDInteractor;
import use_case.SearchByAirlineID.SearchByAirlineIDOutputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportDataAccessInterface;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportInputBoundary;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportInteractor;
import use_case.SearchByDepartureAirport.SearchByDepartureAirportOutputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import use_case.SearchByFlightNumber.SearchByFlightNumberInputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberInteractor;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;
import use_case.SeeWorldMap.SeeWorldMapDataAccessInterface;
import use_case.SeeWorldMap.SeeWorldMapInputBoundary;
import use_case.SeeWorldMap.SeeWorldMapInteractor;
import use_case.SeeWorldMap.SeeWorldMapOutputBoundary;

import javax.swing.*;

public class Application {
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel = new SearchByFlightNumberViewModel();
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel = new SearchByAirlineIDViewModel();
    private final SearchByDepartureAirportViewModel searchByDepartureAirportViewModel = new SearchByDepartureAirportViewModel();
    private final SearchByArrivalAirportViewModel searchByArrivalAirportViewModel = new SearchByArrivalAirportViewModel();
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
        SearchByFlightNumberOutputBoundary outputBoundary = new SearchByFlightNumberPresenter(searchByFlightNumberViewModel);
        SearchByFlightNumberDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchByFlightNumberInputBoundary interactor = new SearchByFlightNumberInteractor(dao, outputBoundary);
        return new SearchByFlightNumberController(interactor);
    }

    private SearchByAirlineIDController createSearchByAirlineIDUseCase() {
        SearchByAirlineIDOutputBoundary outputBoundary = new SearchByAirlineIDPresenter(searchByAirlineIDViewModel);
        SearchByAirlineIDDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchByAirlineIDInputBoundary interactor = new SearchByAirlineIDInteractor(dao, outputBoundary); // Takes both
        return new SearchByAirlineIDController(interactor);
    }

    private SearchByDepartureAirportController createSearchByDepartureUseCase() {
        SearchByDepartureAirportOutputBoundary outputBoundary = new SearchByDepartureAirportPresenter(searchByDepartureAirportViewModel);
        SearchByDepartureAirportDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchByDepartureAirportInputBoundary interactor = new SearchByDepartureAirportInteractor(dao, outputBoundary);
        return new SearchByDepartureAirportController(interactor);
    }

    private SearchByArrivalAirportController createSearchByArrivalAirportUseCase() {
        SearchByArrivalAirportOutputBoundary outputBoundary = new SearchByArrivalAirportPresenter(searchByArrivalAirportViewModel);
        SearchByArrivalAirportDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchByArrivalAirportInputBoundary interactor = new SearchByArrivalAirportInteractor(dao, outputBoundary);
        return new SearchByArrivalAirportController(interactor);
    }

    private SearchAirportLandedController createSearchAirportLandedUseCase() {
        SearchAirportLandedOutputBoundary outputBoundary = new SearchAirportLandedPresenter(searchAirportLandedViewModel);
        SearchAirportLandedDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchAirportLandedInputBoundary interactor = new SearchAirportLandedInteractor(dao, outputBoundary);
        return new SearchAirportLandedController(interactor);
    }

    private SeeWorldMapController createSeeWorldMapUseCase() {
        SeeWorldMapOutputBoundary outputBoundary = new SeeWorldMapPresenter(seeWorldMapViewModel);
        SeeWorldMapDataAccessInterface dao = new All_API_Calling();
        SeeWorldMapInputBoundary interactor = new SeeWorldMapInteractor(dao, outputBoundary);
        return new SeeWorldMapController(interactor);
    }
}
