package app.gui;

import adapters.SearchByAirlineID.SearchByAirlineIDController;
import adapters.SearchByAirlineID.SearchByAirlineIDPresenter;
import adapters.SearchByAirlineID.SearchByAirlineIDViewModel;
import adapters.SearchByFlightNumber.SearchByFlightNumberController;
import adapters.SearchByFlightNumber.SearchByFlightNumberPresenter;
import adapters.SearchByFlightNumber.SearchByFlightNumberViewModel;
import data_access.All_API_Calling;
import use_case.SearchByAirlineID.SearchByAirlineIDDataAccessInterface;
import use_case.SearchByAirlineID.SearchByAirlineIDInputBoundary;
import use_case.SearchByAirlineID.SearchByAirlineIDInteractor;
import use_case.SearchByAirlineID.SearchByAirlineIDOutputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import use_case.SearchByFlightNumber.SearchByFlightNumberInputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberInteractor;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;

import javax.swing.*;

public class Application {
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel = new SearchByFlightNumberViewModel();
    private final SearchByAirlineIDViewModel searchByAirlineIDViewModel = new SearchByAirlineIDViewModel();

    public Application() {
        // Create use cases
        SearchByFlightNumberController searchByFlightNumberController = createSearchByFlightNumberUseCase();
        SearchByAirlineIDController searchByAirlineIDController = createSearchByAirlineIDUseCase();

        // Initialize menu
        JFrame menu = new Menu(
                searchByFlightNumberController,
                searchByFlightNumberViewModel,
                searchByAirlineIDController,
                searchByAirlineIDViewModel
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
}
