package usecase.search_by_airlineid;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchByAirlineIDInputDataTest {

    private SearchByAirlineIDInputData searchByAirlineIDInputData;

    @Before
    public void setUp() {
        // Initialize with a valid IATA code for setup
        searchByAirlineIDInputData = new SearchByAirlineIDInputData("AA");
    }

    @Test
    public void testGetAirlineIataCode() {
        String expected = "AA";
        String actual = searchByAirlineIDInputData.getAirlineIataCode();
        assertEquals("The airline IATA code should be 'AA'", expected, actual);
    }

    @Test
    public void testConstructorWithValidIataCode() {
        String expectedIataCode = "AA";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(expectedIataCode);
        assertEquals("The IATA code should match the input", expectedIataCode, inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithNullIataCode() {
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(null);
        assertNull("The IATA code should be null", inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithEmptyIataCode() {
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData("");
        assertEquals("The IATA code should be an empty string", "", inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithWhitespaceIataCode() {
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData("   ");
        assertEquals("The IATA code should be '   '", "   ", inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithTwoCharacterIataCode() {
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData("XY");
        assertEquals("The IATA code should be 'XY'", "XY", inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithLongIataCode() {
        String longIataCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(longIataCode);
        assertEquals("The IATA code should match the long input", longIataCode, inputData.getAirlineIataCode());
    }

    @Test
    public void testConstructorWithSpecialCharacters() {
        String specialCharsCode = "@$";
        SearchByAirlineIDInputData inputData = new SearchByAirlineIDInputData(specialCharsCode);
        assertEquals("The IATA code should handle special characters", specialCharsCode,
                inputData.getAirlineIataCode());
    }
}
