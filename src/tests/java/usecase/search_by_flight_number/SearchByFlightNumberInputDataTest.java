package usecase.search_by_flight_number;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchByFlightNumberInputDataTest {

    private SearchByFlightNumberInputData searchByFlightNumberInputData;

    @Before
    public void setUp() {
        searchByFlightNumberInputData = new SearchByFlightNumberInputData("AB123");
    }

    @Test
    public void testGetFlightNumber() {
        String expected = "AB123";
        String actual = searchByFlightNumberInputData.getFlightNumber();
        assertEquals("The flight number should be 'AB123'", expected, actual);
    }

    @Test
    public void testConstructorWithValidFlightNumber() {
        String expectedFlightNumber = "AB123";
        SearchByFlightNumberInputData inputData = new SearchByFlightNumberInputData(expectedFlightNumber);
        assertEquals("The flight number should match the input", expectedFlightNumber, inputData.getFlightNumber());
    }

    @Test
    public void testConstructorWithNullFlightNumber() {
        SearchByFlightNumberInputData inputData = new SearchByFlightNumberInputData(null);
        assertNull("The flight number should be null", inputData.getFlightNumber());
    }
}
