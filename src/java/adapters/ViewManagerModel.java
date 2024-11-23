package adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    public enum State {
        SEARCHBYFLIGHT,
        SEARCHBYAIRPORTID,
        SEARCHBYAIRLINEID,
        SEEWORLDMAP,
        MENU
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private State state;

    /**
     * Returns the current state of the ViewModel.
     * @return the current state
     */
    public State getState() {
        return this.state;
    }

    /**
     * Sets the state of the ViewModel.
     * Fires a property change event to notify listeners.
     * @param newState the new state to set
     */
    public void setState(State newState) {
        State oldState = this.state;
        this.state = newState;
        this.support.firePropertyChange("state", oldState, this.state);
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
