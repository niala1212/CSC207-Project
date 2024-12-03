package usecase.see_world_map;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class SeeWorldMapInputDataTest {

    private SeeWorldMapInputData seeWorldMapInputData;

    @Before
    public void setUp() {
        seeWorldMapInputData = new SeeWorldMapInputData("reset");
    }

    @Test
    public void testGetReset() {
        String expected = "reset";
        String actual = seeWorldMapInputData.getReset();
        assertEquals("The return for the input data should be 'reset'", expected, actual);
    }

    @Test
    public void testConstructorWithValidReturn() {
        String expectedresult = "reset";
        SeeWorldMapInputData inputData = new SeeWorldMapInputData(expectedresult);
        assertEquals("The input data should match reset", expectedresult, inputData.getReset());
    }

    @Test
    public void testConstructorWithNullFlightNumber() {
        SeeWorldMapInputData inputData = new SeeWorldMapInputData(null);
        assertEquals("The reset value should return \"\" in the event that null is passed", "" , inputData.getReset());
    }
}
