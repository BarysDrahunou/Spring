package by.epam.inner.date;

import by.epam.inner.myexceptions.CsvLineException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrapperDateTest {
    WrapperDate date;

    @Before
    public void init() throws CsvLineException {
        date = new WrapperDate("18-08-12");
    }

    @Test
    public void testToString() {
        assertEquals(date.toString(), "18-08-12");
    }

    @Test
    public void getYear() {
        assertEquals(date.getYear(), 2018);
    }

    @Test
    public void dayOfWeek() {
        assertEquals(date.dayOfWeek(), DaysOfWeek.Sunday);
    }
}