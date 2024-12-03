package usecase.search_airport_landed;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchAirportLandedInputDataTest {

    @Test
    public void testConstructorWithValidIataCode() {
        String expected = "YYZ";
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(expected);
        assertEquals("The IATA code should match the input", expected, inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithNullIataCode() {
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(null);
        assertNull("The IATA code should be null", inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithEmptyIataCode() {
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData("");
        assertEquals("The IATA code should be an empty string", "", inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithWhitespaceIataCode() {
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData("   ");
        assertEquals("The IATA code should be '   '", "   ", inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithTwoCharacterIataCode() {
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData("XY");
        assertEquals("The IATA code should be 'XY'", "XY", inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithLongIataCode() {
        String longIataCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(longIataCode);
        assertEquals("The IATA code should match the long input", longIataCode, inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithSpecialCharacters() {
        String specialCharsCode = "@$";
        SearchAirportLandedInputData inputData = new SearchAirportLandedInputData(specialCharsCode);
        assertEquals("The IATA code should handle special characters", specialCharsCode,
                inputData.getAirportCode());
    }
}
