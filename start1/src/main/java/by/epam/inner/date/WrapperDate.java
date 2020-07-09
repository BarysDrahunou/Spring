package by.epam.inner.date;

import by.epam.inner.myexceptions.CsvLineException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class WrapperDate {
    final Date date;

    public WrapperDate(String arg) throws CsvLineException {
        try {
            this.date = new SimpleDateFormat("yy-MM-dd").parse(arg);
        } catch (ParseException e) {
            throw new CsvLineException("Illegal date format", arg);
        }
    }

    @Override
    public String toString() {
        return new SimpleDateFormat(
                "yy-MM-dd").format(date);
    }

    private Calendar setDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public int getYear() {
        return setDay(date).get(Calendar.YEAR);
    }

    public DaysOfWeek dayOfWeek() {
        int day = setDay(date).get(Calendar.DAY_OF_WEEK) - 2;
        day = day == -1 ? 6 : day;
        return DaysOfWeek.values()[day];
    }
}
