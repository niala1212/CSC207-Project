package adapters.SeeWorldMap;

import adapters.AbstractState;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The state for the See World Map ViewModel.
 */
public class SeeWorldMapState extends AbstractState {
    private final Map<Point, String> markerInfo = new HashMap<>();

    public List<Point> getMarkers() {
        return List.copyOf(markerInfo.keySet());
    }

    public String getFlightInfo(Point point) {
        return markerInfo.get(point);
    }

    public void addMarker(Point point, String flightInfo) {
        markerInfo.put(point, flightInfo);
//        notifyListeners("seeWorldMapState", null, this);
    }
}

