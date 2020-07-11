package by.epam.inner.date;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.junit.Assert.*;

public class WrapperDateTest {
    WrapperDate wrapperDate1;
    WrapperDate wrapperDate2;

    @Before
    public void init() {
        wrapperDate1 = new WrapperDate("20-07-03");
        wrapperDate2 = new WrapperDate("18-12-12");
    }


    @Test
    public void testToString() {
        assertEquals("20-07-03", wrapperDate1.toString());
        assertEquals("18-12-12", wrapperDate2.toString());
    }

    @Test
    public void getYear() {
        assertEquals(2020, wrapperDate1.getYear());
        assertEquals(2018, wrapperDate2.getYear());
    }

    @Test
    public void getDayOfWeek() {
        assertEquals(DayOfWeek.FRIDAY, wrapperDate1.getDayOfWeek());
        assertEquals(DayOfWeek.WEDNESDAY, wrapperDate2.getDayOfWeek());
    }

}