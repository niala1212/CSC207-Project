package entities;

import java.util.Arrays;

/**
 * Represents an airport with relevant details.
 */
public class Airport {

    // eg: "MNL" for Ninoy Aquino International(the iata code)
    private String airportCode;
    private String name;
    private double[] location;

    // Constructors
    public Airport() {

    }

    public Airport(String airportCode, String name, double[] location) {
        this.airportCode = airportCode;
        this.name = name;
        this.location = location;
    }

    // Getters
    /**
     * Gets the airport code.
     * @return the airport code
     */
    public final String getairportCode() {
        return airportCode;
    }

    public final String getName() {
        return name;
    }

    public final double[] getLocation() {
        return location;
    }

    // Setters
    /**
     * Gets the airport code.
     * @param airportCodes code for airport
     */
    public final void setairportCode(String airportCodes) {
        this.airportCode = airportCodes;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setLocation(double[] location) {
        this.location = location;
    }

    // To String method
    @Override
    public String toString() {
        return "Airport{" + "Airport Code = '" + airportCode + '\'' + ", Name = '" + name + '\'' + ", Location = "
                + (location != null ? Arrays.toString(location) : "N/A") + '}';
    }
}
