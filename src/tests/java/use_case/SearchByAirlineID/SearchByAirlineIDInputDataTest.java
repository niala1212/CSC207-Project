package use_case.SearchByAirlineID;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchByAirlineIDInputDataTest {

    private SearchByAirlineIDInputData searchByAirlineIDInputData;

    @Before
    public void setUp() {
        // Initialize with a valid IATA code
        searchByAirlineIDInputData = new SearchByAirlineIDInputData("AA");
    }

    @Test
    public void testGetAirlineIataCode() {
        // Test if the getter returns the correct IATA code
        String expected = "AA";
        String actual = searchByAirlineIDInputData.getAirlineIataCode();
        assertEquals("The airline IATA code should be 'AA'", expected, actual);
    }

    @Test
    public void testConstructorWithValidIataCode() {
        // Test the constructor with a valid IATA code
        String expectedIataCode = "AA";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(expectedIataCode);
        assertEquals("The IATA code should match the input", expectedIataCode, inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithNullIataCode() {
        // Test the constructor with a null IATA code
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(null);
        assertNull("The IATA code should be null", inputData.getAirlineIataCode());
    }
}
