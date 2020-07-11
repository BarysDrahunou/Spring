package by.epam.inner.date;

import by.epam.inner.myexceptions.WrongArgumentException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public final class WrapperDate {
    private final LocalDate date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd");

    public WrapperDate(String csv) {
        this(getDateFromCsv(csv).orElseThrow(() -> new WrongArgumentException("Illegal date format", csv)));
    }

    public WrapperDate(LocalDate date) {
        this.date = Objects.requireNonNull(date);
    }

    public WrapperDate(WrapperDate date) {
        this(date.date);
    }

    public static Optional<WrapperDate> getDateFromCsv(String csv) {
        try {
            LocalDate date = LocalDate.parse(csv, FORMATTER);
            if (date.isAfter(LocalDate.now())) {
                return Optional.empty();
            }
            return Optional.of(new WrapperDate(date));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return FORMATTER.format(date);
    }

    public int getYear() {
        return date.getYear();
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

}
