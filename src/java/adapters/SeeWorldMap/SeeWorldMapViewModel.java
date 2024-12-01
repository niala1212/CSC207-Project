package adapters.SeeWorldMap;

import adapters.AbstractViewModel;

public class SeeWorldMapViewModel extends AbstractViewModel<SeeWorldMapState> {

    public SeeWorldMapViewModel() {
        super(ViewState.WORLDMAP); // Set the initial view state
        setState(new SeeWorldMapState()); // Set the initial data state
    }
}

