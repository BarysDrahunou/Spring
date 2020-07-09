package by.epam.inner.myexceptions;

public class CsvLineException extends Exception {

    private final String problemValue;
    private final Exception exception;

    public CsvLineException(String message, String problemValue) {
        this.problemValue = problemValue;
        this.exception = new IllegalArgumentException(message);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", problemValue, exception.getMessage());
    }
}