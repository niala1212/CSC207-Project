package app.gui;

import adapters.SearchByFlightNumber.SearchByFlightNumberController;
import adapters.SearchByFlightNumber.SearchByFlightNumberPresenter;
import adapters.SearchByFlightNumber.SearchByFlightNumberViewModel;
import use_case.SearchByFlightNumber.SearchByFlightNumberDataAccessInterface;
import use_case.SearchByFlightNumber.SearchByFlightNumberInputBoundary;
import use_case.SearchByFlightNumber.SearchByFlightNumberInteractor;
import use_case.SearchByFlightNumber.SearchByFlightNumberOutputBoundary;

import javax.swing.*;

/**
 * GUI class to run the GUI for the entire Application.
 */
public class Application {
    private final SearchByFlightNumberViewModel searchByFlightNumberViewModel = new SearchByFlightNumberViewModel();

    public Application() {
        SearchByFlightNumberController searchByFlightNumberController = createSearchByFlightNumberUseCase();

        JFrame menu = new Menu(searchByFlightNumberController, searchByFlightNumberViewModel);
    }

    private SearchByFlightNumberController createSearchByFlightNumberUseCase() {
        SearchByFlightNumberOutputBoundary searchByFlightNumberOutputBoundary = new SearchByFlightNumberPresenter(
                searchByFlightNumberViewModel);
        SearchByFlightNumberInputBoundary searchByFlightNumberInteractor = new SearchByFlightNumberInteractor(
                searchByFlightNumberOutputBoundary);
        return new SearchByFlightNumberController(searchByFlightNumberInteractor);
    }

}
