package adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel class that holds the data and provides the functionality to fire property changes.
 * The class is generic to support different types of state for different views.
 */
public class ViewModel<T> {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private T state; // This will hold the data state, such as SearchByFlightNumberState

    private State viewState; // Enum representing the current view state (e.g., SEARCHBYFLIGHT)

    public enum State {
        SEARCHBYFLIGHT,
        SEARCHBYAIRPORTID,
        SEARCHBYAIRLINEID,
        SEEWORLDMAP,
        MENU
    }

    /**
     * Constructor for ViewModel with a specific view state.
     * @param viewState The initial view state.
     */
    public ViewModel(State viewState) {
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
     * @return the current view state
     */
    public State getViewState() {
        return this.viewState;
    }

    /**
     * Sets the current view state.
     * Fires a property change event to notify listeners.
     * @param newState the new view state to set
     */
    public void setViewState(State newState) {
        State oldState = this.viewState;
        this.viewState = newState;
        this.support.firePropertyChange("viewState", oldState, this.viewState);
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
     * @param listener the PropertyChangeListener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from this ViewModel.
     * @param listener the PropertyChangeListener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }
}
