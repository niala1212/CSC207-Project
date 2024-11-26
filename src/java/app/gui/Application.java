package app.gui;

import adapters.SearchAirportArrivals.SearchAirportArrivalsController;
import adapters.SearchAirportArrivals.SearchAirportArrivalsPresenter;
import adapters.SearchAirportArrivals.SearchAirportArrivalsViewModel;
import adapters.SearchAirportLanded.SearchAirportLandedController;
import adapters.SearchAirportLanded.SearchAirportLandedPresenter;
import adapters.SearchAirportLanded.SearchAirportLandedViewModel;
import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDPresenter;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportController;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportPresenter;
import adapters.SearchByDepartureAirport.SearchByDepartureAirportViewModel;
import adapters.SearchByFlightNumber.SearchByFlightNumberController;
import adapters.SearchByFlightNumber.SearchByFlightNumberPresenter;
import adapters.SearchByFlightNumber.SearchByFlightNumberViewModel;
import data_access.All_API_Calling;
import use_case.SearchAirportArrivals.SearchAirportArrivalsDataAccessInterface;
import use_case.SearchAirportArrivals.SearchAirportArrivalsInputBoundary;
import use_case.SearchAirportArrivals.SearchAirportArrivalsInteractor;
import use_case.SearchAirportArrivals.SearchAirportArrivalsOutputBoundary;
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

import javax.swing.*;

public class Application {
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel = new SearchByFlightNumberViewModel();
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel = new SearchByAirlineIDViewModel();
    private final SearchByDepartureAirportViewModel searchByDepartureAirportViewModel = new SearchByDepartureAirportViewModel();
    private final SearchAirportArrivalsViewModel searchAirportArrivalsViewModel = new SearchAirportArrivalsViewModel();
    private final SearchAirportLandedViewModel searchAirportLandedViewModel = new SearchAirportLandedViewModel();

    public Application() {
        // Create use cases
        final SearchByFlightNumberController searchByFlightNumberController = createSearchByFlightNumberUseCase();
        final SearchByAirlineIDController searchByAirlineIDController = createSearchByAirlineIDUseCase();
        final SearchByDepartureAirportController searchByDepartureAirportController = createSearchByDepartureUseCase();
        final SearchAirportArrivalsController searchAirportArrivalsController = createSearchAirportArrivalsUseCase();
        final SearchAirportLandedController searchAirportLandedController = createSearchAirportLandedUseCase();

        // Initialize menu
        JFrame menu = new Menu(
                searchByFlightNumberViewModel,
                searchByFlightNumberController,
                searchByAirlineIDViewModel,
                searchByAirlineIDController,
                searchByDepartureAirportViewModel,
                searchByDepartureAirportController,
                searchAirportArrivalsViewModel,
                searchAirportArrivalsController,
                searchAirportLandedViewModel,
                searchAirportLandedController
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

    private SearchAirportArrivalsController createSearchAirportArrivalsUseCase() {
        SearchAirportArrivalsOutputBoundary outputBoundary = new SearchAirportArrivalsPresenter(searchAirportArrivalsViewModel);
        SearchAirportArrivalsDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchAirportArrivalsInputBoundary interactor = new SearchAirportArrivalsInteractor(dao, outputBoundary);
        return new SearchAirportArrivalsController(interactor);

    }

    private SearchAirportLandedController createSearchAirportLandedUseCase() {
        SearchAirportLandedOutputBoundary outputBoundary = new SearchAirportLandedPresenter(searchAirportLandedViewModel);
        SearchAirportLandedDataAccessInterface dao = new All_API_Calling(); // DAO
        SearchAirportLandedInputBoundary interactor = new SearchAirportLandedInteractor(dao, outputBoundary);
        return new SearchAirportLandedController(interactor);

    }
}
