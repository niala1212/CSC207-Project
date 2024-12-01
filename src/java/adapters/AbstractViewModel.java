package adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class that holds the data and provides the functionality to fire property changes.
 * The class is generic to support different types of state for different views.
 * @param <T> holds the data state
 */
public abstract class AbstractViewModel<T> {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Holds the data state, such as SearchByFlightNumberState
    private T state;

    // Enum representing the current view state (e.g., SEARCHBYFLIGHT)
    private ViewState viewState;

    /**
     * Enum representing the different states of the application's view.
     * <ul>
     *     <li>{@link #SEARCHBYFLIGHT} - Search by flight number.</li>
     *     <li>{@link #SEARCHBYAIRPORTID} - Search by airport ID.</li>
     *     <li>{@link #SEARCHBYAIRLINEID} - Search by airline ID.</li>
     *     <li>{@link #WORLDMAP} - Display world map.</li>
     *     <li>{@link #MENU} - Show the main menu.</li>
     * </ul>
     */
    public enum ViewState {
        SEARCHBYFLIGHT,
        SEARCHBYAIRPORTID,
        SEARCHBYAIRLINEID,
        WORLDMAP,
        MENU
    }

    /**
     * Constructor for ViewModel with a specific view state.
     * @param viewState The initial view state.
     */
    public AbstractViewModel(ViewState viewState) {
        this.viewState = viewState;
    }

    /**
     * Returns the current state of the ViewModel (the data state).
     * @return the current data state
     */
    public T getState() {
        return this.state;
    }

    /**
     * Sets the state of the ViewModel (the data state).
     * Fires a property change event to notify listeners.
     * @param newState the new data state to set
     */
    public void setState(T newState) {
        T oldState = this.state;
        this.state = newState;
        this.support.firePropertyChange("state", oldState, this.state);
    }

    /**
     * Returns the current view state (the enum representing the view).
     * @return current view state
     */
    public ViewState getViewState() {
        return this.viewState;
    }

    /**
     * Sets the current view state.
     * Fires a property change event to notify listeners of change.
     * @param newViewState the new view state
     */
    public void setViewState(ViewState newViewState) {
        ViewState oldViewState = this.viewState;
        this.viewState = newViewState;
        this.support.firePropertyChange("viewState", oldViewState, this.viewState);
    }

    /**
     * Fires a property change event with a specific property name.
     * @param propertyName the name of the property that changed
     */
    public void firePropertyChanged(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * @param listener to add the PropertyChangeListener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from this ViewModel.
     * @param listener to remove the PropertyChangeListener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    /**
     * Reset the state to default.
     */
    public void clearView() {
        // This could also trigger state-specific clearing logic (e.g., clear error messages)
        setState(null);
        firePropertyChanged("clearView");
    }
}
