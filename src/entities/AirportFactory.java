package entities;

/**
 * Factory for creating Airport objects.
 */

public class AirportFactory {

    public Airport create(String name, String location) {
        return new Airport(name,location);
    }
}