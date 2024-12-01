package usecase.search_by_departure_airport;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import usecase.search_by_arrival_airport.SearchByArrivalAirportInputData;

public class SearchByDepartureAirportInputDataTest {

    private SearchByDepartureAirportInputData searchByDepartureAirportInputData;

    @Before
    public void setUp() {
        searchByDepartureAirportInputData = new SearchByDepartureAirportInputData("SFO");
    }

    @Test
    public void testGetDepartureAirportCode() {
        String expected = "SFO";
        String actual = searchByDepartureAirportInputData.getAirportCode();
        assertEquals("The arrival airport code should be 'SFO'", expected, actual);
    }

    @Test
    public void testConstructorWithValidAirportCode() {
        String expectedAirportCode = "SFO";
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(expectedAirportCode);
        assertEquals("The airport code should match the input", expectedAirportCode, inputData.getAirportCode());
    }

    @Test
    public void testConstructorWithNullAirportCode() {
        SearchByArrivalAirportInputData inputData = new SearchByArrivalAirportInputData(null);
        assertNull("The airport code should be null", inputData.getAirportCode());
    }
}
