package usecase.search_by_arrival_airport;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchByArrivalAirportInputDataTest {

    private SearchByArrivalAirportInputData searchByArrivalAirportInputData;

    @Before
    public void setUp() {
        searchByArrivalAirportInputData = new SearchByArrivalAirportInputData("JFK");
    }

    @Test
    public void testGetArrivalAirportCode() {
        String expected = "JFK";
        String actual = searchByArrivalAirportInputData.getAirportCode();
        assertEquals("The arrival airport code should be 'JFK'", expected, actual);
    }

    @Test
    public void testConstructorWithValidAirportCode() {
        String expectedAirportCode = "JFK";
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(expectedAirportCode);
        assertEquals("The airport code should match the input", expectedAirportCode, inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithNullAirportCode() {
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(null);
        assertNull("The airport code should be null", inputData.getAirportCode());
    }
}
