package adapters.see_world_map;

import adapters.AbstractViewModel;

public class SeeWorldMapViewModel extends AbstractViewModel<SeeWorldMapState> {

    public SeeWorldMapViewModel() {
        // Set the initial view state
        super(ViewState.WORLDMAP);
        // Set the initial data state
        setState(new SeeWorldMapState());
    }
}

