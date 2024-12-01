package usecase.SeeWorldMap;

import entities.Flight;
import java.util.List;

/**
 * The interface of the DAO for the SeeWorldMap Use Case.
 */
public interface SeeWorldMapDataAccessInterface {

    /**
     * Retrieves the flights from the system.
     * @return a list of flights
     */
    List<Flight> getRandomFlights();
}
