package entities;

/**
 * Represents an airline with relevant details.
 */
public class Airline {
    // e.g., "AA" for American Airlines (the iata code)
    private String airlineCode;
    // Full name of the airline
    private String name;

    // Constructors
    public Airline() {

    }

    public Airline(String airlineCode, String name) {
        this.airlineCode = airlineCode;
        this.name = name;
    }

    // Getters
    public final String getAirlineCode() {
        return airlineCode;
    }

    public final String getName() {
        return name;
    }

    // Setters
    public final void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public final void setName(String name) {
        this.name = name;
    }

    // string method
    @Override
    public String toString() {
        return airlineCode + "{" + ", Name ='" + name + '\'' + '}';
    }
}
