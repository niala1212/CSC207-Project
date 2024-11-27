package use_case.SearchByAirlineID;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchByAirlineIDInputDataTest {

    private SearchByAirlineIDInputData searchByAirlineIDInputData;

    // Set up method to initialize the test environment
    @Before
    public void setUp() {
        // Initialize the SearchByAirlineIDInputData object with a valid IATA code "AA"
        searchByAirlineIDInputData = new SearchByAirlineIDInputData("AA");
    }

    // Test case for the getter method of the IATA code
    @Test
    public void testGetAirlineIataCode() {
        // Expected IATA code is "AA"
        String expected = "AA";

        // Get the actual IATA code from the object
        String actual = searchByAirlineIDInputData.getAirlineIataCode();

        // Assert that the expected value matches the actual value
        assertEquals("The airline IATA code should be 'AA'", expected, actual);
    }

    // Test case for the constructor with a valid IATA code
    @Test
    public void testConstructorWithValidIataCode() {
        // Define the expected IATA code
        String expectedIataCode = "AA";

        // Create a new SearchByAirlineIDInputData object with the valid IATA code
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(expectedIataCode);

        // Assert that the IATA code set by the constructor matches the expected IATA code
        assertEquals("The IATA code should match the input", expectedIataCode, inputData.getAirlineIataCode());
    }

    // Test case for the constructor with a null IATA code
    @Test
    public void testConstructorWithNullIataCode() {
        // Create a new SearchByAirlineIDInputData object with a null IATA code
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(null);

        // Assert that the IATA code is null
        assertNull("The IATA code should be null", inputData.getAirlineIataCode());
    }
}
