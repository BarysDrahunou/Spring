package by.epam.inner.myexceptions;

public class WrongArgumentException extends IllegalArgumentException {
    private final IllegalArgumentException exception;
    private final String problemValue;

    public WrongArgumentException(String message, String problemValue) {
        this.exception = new IllegalArgumentException(message);
        this.problemValue = problemValue;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", problemValue, exception.getMessage());
    }
}
