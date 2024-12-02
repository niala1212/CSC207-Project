package usecase.see_world_map;

import java.io.IOException;
import java.util.List;

import entities.Flight;

/**
 * The interface of the DAO for the SeeWorldMap Use Case.
 */
public interface SeeWorldMapDataAccessInterface {

    /**
     * Retrieves the flights from the system.
     * @return a list of flights.
     * @throws IOException if url runs into an error.
     */
    List<Flight> getRandomFlights() throws IOException;
}
